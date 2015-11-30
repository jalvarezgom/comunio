package com.example.all_pc.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by xEV on 15/11/2015.
 */
public class FnTab2Plantilla extends Fragment implements View.OnClickListener {
    private DataBaseManager manager;
    private ListView listView;
    private List<Player> players;
    private TextView Tdinero;
    private final int FRAGMENT_GROUPID = 30;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.lay_tab2plantilla, container, false);
        manager = new DataBaseManager(getActivity());
        Tdinero = (TextView) rootView.findViewById(R.id.textDinero);
        listView = (ListView) rootView.findViewById(R.id.listPlantilla);
        String aux= manager.getTeam_user(Login.id_user);
        int i= manager.getMoney(aux);
        Tdinero.setText(String.valueOf(i));
        //final DataBaseManager manager = new DataBaseManager(getActivity());
        players = manager.getJugadoresDeEquipo(aux);
        listView.setAdapter(new PlayerAdapter(getActivity(),players));

        registerForContextMenu(listView);

        return rootView;
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getActivity().getMenuInflater();

            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo)menuInfo;

            menu.setHeaderTitle(
                    players.get(info.position).getNombre());

            inflater.inflate(R.menu.menu_tab2plantilla, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.etq1Informacion:
                players.get(info.position);
                Toast.makeText(getActivity(), "Informacion", Toast.LENGTH_LONG).show();
                return true;
            case R.id.etq2Venta:
                String aux= manager.getTeam_user(Login.id_user);
                manager.venderjugador(manager.getTeam_user(Login.id_user),aux);
                Toast.makeText(getActivity(), players.get(info.position).getNombre()+" ha sido vendido.", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*case R.id.bLogout:
                startActivity(new Intent(this, Login.class));
                break;*/}


    }
}
