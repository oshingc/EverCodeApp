package info.androidhive.slidingmenu;

import javax.xml.parsers.FactoryConfigurationError;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
	
	public ProfileFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        
        TextView t1 = (TextView)rootView.findViewById(R.id.nombre_completo);
        TextView t2 = (TextView)rootView.findViewById(R.id.pais);
        
        
        
        Button button = (Button) rootView.findViewById(R.id.btn_edit_profile);
        	   button.setOnClickListener(new OnClickListener()
        	   {
        	             @Override
        	             public void onClick(View v)
        	             {

        	         		Log.d("myTag","ProfileFragment.editProfile()");
        	         	
        	         		
        	             } 
        	   }); 

         
        return rootView;
    }
	
	public void editProfile(){
		Log.d("myTag","ProfileFragment.editProfile()");
		
	}
}
