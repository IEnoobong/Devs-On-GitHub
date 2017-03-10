package co.enoobong.devsongithub.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import co.enoobong.devsongithub.R;
import co.enoobong.devsongithub.adapter.DevelopersAdapter;
import co.enoobong.devsongithub.model.ApiResponse;
import co.enoobong.devsongithub.network.ApiClient;
import co.enoobong.devsongithub.network.ApiInterface;
import co.enoobong.devsongithub.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(String.format(getString(R.string.app_title), Utils.LANGUAGE, Utils.LOCATION));

        if (Utils.isNetworkAvailable(this)) {
            initializeUI();
            getJavaDevsInLagos();
        } else {
            Utils.showDialog(this, android.R.drawable.ic_dialog_alert, R.string.no_network)
                    .setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(Settings.ACTION_SETTINGS));
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
    }

    private void initializeUI() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) findViewById(R.id.devsRecycler);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void getJavaDevsInLagos(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse> call = apiService.getJavaDevsInLagos(Utils.SEARCH_TERMS);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                progressBar.setVisibility(ProgressBar.INVISIBLE);
                recyclerView.setAdapter(new DevelopersAdapter(MainActivity.this, response.body().getDevelopers()));
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                progressBar.setVisibility(ProgressBar.INVISIBLE);
                Toast.makeText(MainActivity.this, R.string.error, Toast.LENGTH_LONG).show();
                Log.e(MainActivity.class.getSimpleName(), t.toString());
            }
        });
    }
}
