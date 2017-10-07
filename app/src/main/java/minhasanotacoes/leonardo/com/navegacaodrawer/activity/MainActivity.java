package minhasanotacoes.leonardo.com.navegacaodrawer.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import minhasanotacoes.leonardo.com.navegacaodrawer.R;
import minhasanotacoes.leonardo.com.navegacaodrawer.fragment.CanalYoutubeFragment;
import minhasanotacoes.leonardo.com.navegacaodrawer.fragment.EncontreUmaIgrejaFragment;
import minhasanotacoes.leonardo.com.navegacaodrawer.fragment.RadioFragment;
import minhasanotacoes.leonardo.com.navegacaodrawer.fragment.SobreNosFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviaEmail();
            }
        });

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //Intent radioActivity = new Intent(this, RadioActivity.class);




        if (id == R.id.nav_radio) {
            //startActivity(radioActivity);
            setTitle("Rádio");
            RadioFragment radioFragment = new RadioFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragmento, radioFragment).commit();

        } else if (id == R.id.nav_sobre_nos) {
            //startActivity(radioActivity);
            setTitle("Sobre nós");
            SobreNosFragment sobreNosFragment = new SobreNosFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragmento, sobreNosFragment).commit();

        } else if (id == R.id.nav_encontre_uma_igreja) {
            setTitle("Encontre Uma Igreja");
            EncontreUmaIgrejaFragment encontreUmaIgrejaFragment = new EncontreUmaIgrejaFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragmento, encontreUmaIgrejaFragment).commit();
        } else if (id == R.id.nav_canal_youtube) {
            setTitle("Canal no Youtube");
            CanalYoutubeFragment canalYoutubeFragment = new CanalYoutubeFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragmento, canalYoutubeFragment).commit();

        } else if (id == R.id.nav_share) {
            compartilheAplicativo();
        } else if (id == R.id.nav_send) {
            enviaEmail();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void compartilheAplicativo(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Baixe nosso aplicativo no seguinte link da PlayStore: https://play.google.com/store/apps/details?id=aquiSeriaOIdDoAplicativo");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void enviaEmail(){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"leonardosoaresalves7@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Aqui vai o assunto do e-mail");
        i.putExtra(Intent.EXTRA_TEXT   , "Aqui o corpo da mensagem");
        try {
            startActivity(Intent.createChooser(i, "Enviando email..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "Não existe nenhum aplicativo para enviar e-mail neste celular.", Toast.LENGTH_SHORT).show();
        }
    }

}
