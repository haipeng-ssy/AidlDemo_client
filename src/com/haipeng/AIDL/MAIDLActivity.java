package com.haipeng.AIDL;



import com.haipeng.R;

import android.support.v7.app.ActionBarActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MAIDLActivity extends ActionBarActivity {

	Button btn_bind,btn_unbind,btn_calculate;
	TextView tv;
	example mService;
	private ServiceConnection serviceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			mService = null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			mService = example.Stub.asInterface(service);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maidl);
		final Intent intent = new Intent();
		intent.setAction("com.haipeng.aidlservice");
		
		btn_bind = (Button) findViewById(R.id.btn_bind);
		btn_unbind = (Button) findViewById(R.id.btn_unbind);
		btn_calculate = (Button) findViewById(R.id.btn_calculate);
		tv          =     (TextView) findViewById(R.id.tv);
		btn_bind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
			}
		});
		btn_unbind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				unbindService(serviceConnection);
			}
		});
		btn_calculate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int a =3;
				int b =4;
				try {
					int sum = mService.add(a, b);
					tv.setText(sum+"");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.maidl, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
