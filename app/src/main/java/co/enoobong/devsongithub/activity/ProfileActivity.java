package co.enoobong.devsongithub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import co.enoobong.devsongithub.R;
import co.enoobong.devsongithub.model.Developer;
import co.enoobong.devsongithub.utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by owner on 5/3/2017.
 */

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private Developer developer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_screen);

        CircleImageView profileImage = (CircleImageView) findViewById(R.id.profile_image);
        TextView username = (TextView) findViewById(R.id.username);
        TextView profileUrl = (TextView) findViewById(R.id.profile_url);
        Button shareProfile = (Button) findViewById(R.id.share_profile);

        developer = getIntent().getParcelableExtra(Utils.DEVELOPERS);
        if(developer != null){
            Glide.with(this)
                    .load(developer.getImageUrl())
                    .placeholder(R.drawable.progress_animation)
                    .dontAnimate()
                    .into(profileImage);
            username.setText(developer.getUsername());
            profileUrl.setText(developer.getProfileUrl());
            shareProfile.setText(String.format(getString(R.string.share_profile), developer.getUsername()));
            shareProfile.setOnClickListener(this);
        } else {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.share_profile:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, String.format(getString(R.string.share_text), developer.getUsername(), developer.getProfileUrl()));
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getString(R.string.share_with)));
                break;
        }
    }
}
