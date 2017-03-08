package co.enoobong.devsongithub.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import co.enoobong.devsongithub.R;
import co.enoobong.devsongithub.activity.ProfileActivity;
import co.enoobong.devsongithub.model.Developer;
import co.enoobong.devsongithub.utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by owner on 5/3/2017.
 */

public class DevelopersAdapter extends RecyclerView.Adapter<DevelopersAdapter.DeveloperViewHolder> {

    private Context context;
    private List<Developer> developers;

    public DevelopersAdapter(final Context context, final List<Developer> developers){
        this.context = context;
        this.developers = developers;
    }

    @Override
    public DeveloperViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dev_item, parent, false);
        return new DeveloperViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DeveloperViewHolder holder, final int position) {
        Developer developer = developers.get(position);
        holder.setUserImage(context, developer.getImageUrl());
        holder.setUsername(developer.getUsername());
        holder.setPosition(position + 1);
    }

    @Override
    public int getItemCount() {
        return developers.size();
    }


    public class DeveloperViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final CircleImageView profileImage;
        private final TextView username, position;

        public DeveloperViewHolder(final View itemView) {
            super(itemView);
            profileImage = (CircleImageView) itemView.findViewById(R.id.profile_image);
            username = (TextView) itemView.findViewById(R.id.username);
            position = (TextView) itemView.findViewById(R.id.position);
            itemView.setOnClickListener(this);
        }

        void setUserImage(final Context context, final String imageUrl){
            if(!TextUtils.isEmpty(imageUrl)){
                Glide.with(context)
                        .load(imageUrl)
                        .placeholder(R.drawable.progress_animation)
                        .dontAnimate()
                        .into(profileImage);
            } else {
                Toast.makeText(context, "Something very bad happened", Toast.LENGTH_LONG).show();
            }
        }

        void setUsername(final String username){
            if(!TextUtils.isEmpty(username)){
                this.username.setText(username);
            } else{
                Toast.makeText(context, "Something very bad happened", Toast.LENGTH_LONG).show();
            }
        }

        void setPosition(final int position) {
            this.position.setText(toOrdinal(position));
        }

        String toOrdinal(final int number) {
            if (number >= 11 && number <= 13) {
                return number + "th";
            }
            switch (number % 10) {
                case 1:
                    return number + "st";
                case 2:
                    return number + "nd";
                case 3:
                    return number + "rd";
                default:
                    return number + "th";
            }
        }

        @Override
        public void onClick(final View view) {
            Intent intent = new Intent(context, ProfileActivity.class);
            intent.putExtra(Utils.DEVELOPERS, developers.get(getAdapterPosition()));
            context.startActivity(intent);
        }
    }
}
