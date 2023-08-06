package News;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navdrawer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ActualitesFragment  extends Fragment {

    List<SliderItems> sliderItems=new ArrayList<> ();
    ArrayList<String>titles=new ArrayList<> ();
    ArrayList<String>desc=new ArrayList<> ();
    ArrayList<String>images=new ArrayList<> ();
    ArrayList<String>newslinks=new ArrayList<> ();
    ArrayList<String>heads=new ArrayList<> ();
    //database reference
    DatabaseReference mRef;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );
        final VerticalViewPager verticalViewPager=(VerticalViewPager) view.findViewById ( R.id.verticalViewPager );

        mRef= FirebaseDatabase.getInstance ().getReference ("News");
        mRef.addListenerForSingleValueEvent ( new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren ())
                {
                    //add data to array list
                    titles.add ( ds.child ( "title" ).getValue (String.class) );
                    desc.add ( ds.child ( "desc" ).getValue (String.class) );
                    images.add ( ds.child ( "imagelink" ).getValue (String.class) );
                    newslinks.add ( ds.child ( "newslinks" ).getValue (String.class) );
                    heads.add ( ds.child ( "head" ).getValue (String.class) );
                }
                for (int i=0;i<images.size ();i++)
                {
                    //here we add slider items with images that are store in images array list
                    sliderItems.add ( new SliderItems ( images.get ( i ) ) );

                }
                verticalViewPager.setAdapter(new ViewPagerAdapter (requireContext (),
                        sliderItems,
                        titles,
                        desc,
                        newslinks,
                        heads,
                        verticalViewPager));
//now add all array list
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate ( R.layout.actualitesfragment,container, false );
    }
}
