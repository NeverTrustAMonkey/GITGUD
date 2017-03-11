package edu.tacoma.uw.plsanch.gitgud.guide;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.tacoma.uw.plsanch.gitgud.R;

import java.util.List;

/**
 * The MyGuideRecyclerViewAdapter holds and displays the list of guides
 * specified {@link GuideFragment.OnListFragmentInteractionListener}.
 */
public class MyGuideRecyclerViewAdapter extends RecyclerView.Adapter<MyGuideRecyclerViewAdapter.ViewHolder> {

    //List of the items
    private final List<Guide> mValues;
    //Onfragmentinterationlistener for the guides located in GuideFragment
    private final GuideFragment.OnListFragmentInteractionListener mListener;

    /**
     * MyGuideRecyclerViewAdapter is a constructor and sets the class variables
     * @param items is the list of items
     * @param listener is the listener for fragment interaction in GuideFragment
     */
    public MyGuideRecyclerViewAdapter(List<Guide> items, GuideFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    /**
     * onCreateViewHolder inflates the views
     * @param parent is the calling parent
     * @param viewType is an unused variable
     * @return the ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_guide, parent, false);
        return new ViewHolder(view);
    }

    /**
     * onBindViewHolder builds and sets the display of the view
     * @param holder is the viewholder
     * @param position is the position on the item list
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getmGuideTitle());
        holder.mContentView.setText("By: " + mValues.get(position).getmGuideAuthor());
        String heroIconName = mValues.get(position).getmGuideHero().toLowerCase();
        setIcon(holder, heroIconName);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    /**
     * setIcon displays the proper hero for the item
     * @param holder is the calling viewHolder
     * @param heroIconName is a string holding the heroname that is checked against all known heroes.
     */
    private void setIcon(ViewHolder holder, String heroIconName){
        if(heroIconName.equals("genji"))holder.mIconView.setImageResource(R.mipmap.icon_genji);
        if(heroIconName.equals("mccree"))holder.mIconView.setImageResource(R.mipmap.icon_mccree);
        if(heroIconName.equals("pharah"))holder.mIconView.setImageResource(R.mipmap.icon_pharah);
        if(heroIconName.equals("reaper"))holder.mIconView.setImageResource(R.mipmap.icon_reaper);
        if(heroIconName.equals("soldier76"))holder.mIconView.setImageResource(R.mipmap.icon_soldier76);
        if(heroIconName.equals("sombra"))holder.mIconView.setImageResource(R.mipmap.icon_sombra);
        if(heroIconName.equals("tracer"))holder.mIconView.setImageResource(R.mipmap.icon_tracer);
        if(heroIconName.equals("bastion"))holder.mIconView.setImageResource(R.mipmap.icon_bastion);
        if(heroIconName.equals("hanzo"))holder.mIconView.setImageResource(R.mipmap.icon_hanzo);
        if(heroIconName.equals("junkrat"))holder.mIconView.setImageResource(R.mipmap.icon_junkrat);
        if(heroIconName.equals("mei"))holder.mIconView.setImageResource(R.mipmap.icon_mei);
        if(heroIconName.equals("torbjorn"))holder.mIconView.setImageResource(R.mipmap.icon_torbjorn);
        if(heroIconName.equals("widowmaker"))holder.mIconView.setImageResource(R.mipmap.icon_widowmaker);
        if(heroIconName.equals("d.va"))holder.mIconView.setImageResource(R.mipmap.icon_dva);
        if(heroIconName.equals("reinhardt"))holder.mIconView.setImageResource(R.mipmap.icon_reinhardt);
        if(heroIconName.equals("roadhog"))holder.mIconView.setImageResource(R.mipmap.icon_roadhog);
        if(heroIconName.equals("winston"))holder.mIconView.setImageResource(R.mipmap.icon_winston);
        if(heroIconName.equals("zarya"))holder.mIconView.setImageResource(R.mipmap.icon_zarya);
        if(heroIconName.equals("ana"))holder.mIconView.setImageResource(R.mipmap.icon_ana);
        if(heroIconName.equals("lucio"))holder.mIconView.setImageResource(R.mipmap.icon_lucio);
        if(heroIconName.equals("mercy"))holder.mIconView.setImageResource(R.mipmap.icon_mercy);
        if(heroIconName.equals("symmetra"))holder.mIconView.setImageResource(R.mipmap.icon_symmetra);
        if(heroIconName.equals("zenyatta"))holder.mIconView.setImageResource(R.mipmap.icon_zenyatta);
    }

    /**
     * getItemCount checks the number of items
     * @return the size of the Values List
     */
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * ViewHolder holds the data from the item
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Guide mItem;
        public final ImageView mIconView;

        /**
         * constructs the data holder
         * @param view is the calling view
         */
        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mIconView = (ImageView) view.findViewById(R.id.browserIcon);
        }

        /**
         * toString returns a string
         * @return is a string object built from the contentView(author) of the guide
         */
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
