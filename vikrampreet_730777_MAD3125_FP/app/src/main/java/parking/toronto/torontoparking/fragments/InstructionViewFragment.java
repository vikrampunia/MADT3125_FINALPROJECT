package parking.toronto.torontoparking.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import parking.toronto.torontoparking.R;

public class InstructionViewFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return loadHTMLFile(inflater.inflate(R.layout.fragment_insruction, container, false));
    }

    private View loadHTMLFile(View inflate) {
        WebView webView = inflate.findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/instruction.html");
        return inflate;
    }


}