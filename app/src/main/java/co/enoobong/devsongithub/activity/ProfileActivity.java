package co.enoobong.devsongithub.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import co.enoobong.devsongithub.R;
import co.enoobong.devsongithub.model.Developer;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by owner on 5/3/2017.
 */

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_screen);
        CircleImageView profileImage = (CircleImageView) findViewById(R.id.profile_image);
        TextView username = (TextView) findViewById(R.id.username);
        TextView profileUrl = (TextView) findViewById(R.id.profile_url);
        Button shareProfile = (Button) findViewById(R.id.share_profile);

        Developer developer = getIntent().getParcelableExtra("Developer");

        username.setText(developer != null ? developer.getUsername() : "Error Occurred");
        profileUrl.setText(developer != null ? developer.getProfileUrl() : "Error Occurred");
        shareProfile.setText(String.format(getString(R.string.share_profile), developer != null ? developer.getUsername() : "Error Occurred"));
        Glide.with(this)
                .load(developer != null ? developer.getImageUrl() : R.drawable.profile_pic)
                .dontAnimate()
                .into(profileImage);
    }
}
