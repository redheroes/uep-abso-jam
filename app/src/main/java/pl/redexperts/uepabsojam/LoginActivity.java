package pl.redexperts.uepabsojam;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class LoginActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
                t.start();
                synchronized(t){
                    try{
                        t.wait(1000);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

}
