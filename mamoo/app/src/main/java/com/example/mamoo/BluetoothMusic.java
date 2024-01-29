package com.example.mamoo;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.media.AudioManager;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.UUID;
import android.net.Uri;

public class BluetoothMusic extends AppCompatActivity {
    private int resId;
    private Button btn_play;
    private Button btn_stop;

    MediaPlayer mediaPlayer;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket bluetoothSocket;
    private InputStream inputStream;
    private Button bluetoothButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_music);
//        Intent intent = getIntent();
//        String str = intent.getStringExtra("str");
        bluetoothButton = findViewById(R.id.bluetoothButton);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Bluetooth 장치 선택 및 연결
        connectToBluetoothDevice();
        // 블루투스 스피커로 오디오 전송 버튼 클릭 이벤트 처리
        bluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    bluetoothButton.setText("재생");
                } else {
                    mediaPlayer.start();
                    bluetoothButton.setText("일시정지");
                }
            }
        });
    }

    private void connectToBluetoothDevice() {
        if (bluetoothAdapter == null) {
            // 블루투스가 지원되지 않는 경우 처리
            return;
        }

        if (!bluetoothAdapter.isEnabled()) {
            // 블루투스가 비활성화된 경우, 활성화 요청을 사용자에게 보여줄 수 있습니다.
            // 여기서는 활성화 요청을 생략하고 사용자에게 활성화하도록 요청하거나 설정 액티비티로 이동하는 코드를 추가할 수 있습니다.
            return;
        }

        // 페어링된 블루투스 장치 목록 가져오기
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                // 여기에서 블루투스 스피커를 찾거나 필요한 블루투스 장치를 선택합니다.
                if (device.getName().equals("my mac")) {
                    bluetoothDevice = device;
                    break;
                }
            }

            if (bluetoothDevice != null) {
                // BluetoothSocket 초기화 및 연결
                connectBluetoothSocket();
            }
        }
    }

    private void connectBluetoothSocket() {
        try {
//            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // SPP 프로필 UUID
//            final static UUID BT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // 아두이노
            UUID uuid = UUID.fromString("8CE255C0-200A-11E0-AC64-0800200C9A66"); // 스마트폰

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
            bluetoothSocket.connect();

            inputStream = bluetoothSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // MediaPlayer 초기화 및 오디오 스트리밍 시작
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        String str = "sample_music";

        resId = getResources().getIdentifier(str, "raw", getPackageName());

        String url = "android.resource://" + getPackageName() + "/" + R.raw.sample_music;
        try {
//            mediaPlayer = MediaPlayer.create(this, resId);
//            mediaPlayer.setDataSource(inputStream.getFD()); // setDataSource 안에 string으로 파일 경로 지정하여 사용
            mediaPlayer.setDataSource(this, Uri.parse(url));
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        String str = "sample_music";
//
//        resId = getResources().getIdentifier(str, "raw", getPackageName());

//        if (resId != 0) {
//            mediaPlayer = MediaPlayer.create(this, resId);
//            mediaPlayer.start();


//        btn_play = (Button) findViewById(R.id.btn_play);
//        btn_play.setOnClickListener(v -> {
//            mediaPlayer = MediaPlayer.create(this, resId);
//            mediaPlayer.start();
//        });
//
//        btn_stop = (Button) findViewById(R.id.btn_stop);
//        btn_stop.setOnClickListener(v -> {
//            mediaPlayer.stop();
//        });
    }
}

//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//import android.bluetooth.BluetoothSocket;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.SystemClock;
////import android.support.v7.app.AlertDialog;
////import android.support.v7.app.AppCompatActivity;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//import java.util.UUID;
//
//public class BluetoothMusic extends AppCompatActivity {
//    TextView mTvBluetoothStatus;
//    TextView mTvReceiveData;
//    TextView mTvSendData;
//    Button mBtnBluetoothOn;
//    Button mBtnBluetoothOff;
//    Button mBtnConnect;
//    Button mBtnSendData;
//
//    BluetoothAdapter mBluetoothAdapter;
//    Set<BluetoothDevice> mPairedDevices;
//    List<String> mListPairedDevices;
//
//    Handler mBluetoothHandler;
//    ConnectedBluetoothThread mThreadConnectedBluetooth;
//    BluetoothDevice mBluetoothDevice;
//    BluetoothSocket mBluetoothSocket;
//
//    final static int BT_REQUEST_ENABLE = 1;
//    final static int BT_MESSAGE_READ = 2;
//    final static int BT_CONNECTING_STATUS = 3;
////    final static UUID BT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // 아두이노
//    final static UUID BT_UUID = UUID.fromString("8CE255C0-200A-11E0-AC64-0800200C9A66"); // 스마트폰
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.bluetooth_music);
//
//        mTvBluetoothStatus = (TextView) findViewById(R.id.tvBluetoothStatus);
//        mTvReceiveData = (TextView) findViewById(R.id.tvReceiveData);
//        mTvSendData = (EditText) findViewById(R.id.tvSendData);
//        mBtnBluetoothOn = (Button) findViewById(R.id.btnBluetoothOn);
//        mBtnBluetoothOff = (Button) findViewById(R.id.btnBluetoothOff);
//        mBtnConnect = (Button) findViewById(R.id.btnConnect);
//        mBtnSendData = (Button) findViewById(R.id.btnSendData);
//
//        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//
//
//        mBtnBluetoothOn.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                bluetoothOn();
//            }
//        });
//        mBtnBluetoothOff.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                bluetoothOff();
//            }
//        });
//        mBtnConnect.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listPairedDevices();
//            }
//        });
//        mBtnSendData.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mThreadConnectedBluetooth != null) {
//                    mThreadConnectedBluetooth.write(mTvSendData.getText().toString());
//                    mTvSendData.setText("");
//                }
//            }
//        });
//        mBluetoothHandler = new Handler() {
//            public void handleMessage(android.os.Message msg) {
//                if (msg.what == BT_MESSAGE_READ) {
//                    String readMessage = null;
//                    try {
//                        readMessage = new String((byte[]) msg.obj, "UTF-8");
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//                    mTvReceiveData.setText(readMessage);
//                }
//            }
//        };
//    }
//
//    void bluetoothOn() {
//        if (mBluetoothAdapter == null) {
//            Toast.makeText(getApplicationContext(), "블루투스를 지원하지 않는 기기입니다.", Toast.LENGTH_LONG).show();
//        } else {
//            if (mBluetoothAdapter.isEnabled()) {
//                Toast.makeText(getApplicationContext(), "블루투스가 이미 활성화 되어 있습니다.", Toast.LENGTH_LONG).show();
//                mTvBluetoothStatus.setText("활성화");
//            } else {
//                Toast.makeText(getApplicationContext(), "블루투스가 활성화 되어 있지 않습니다.", Toast.LENGTH_LONG).show();
//                Intent intentBluetoothEnable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
//                startActivityForResult(intentBluetoothEnable, BT_REQUEST_ENABLE);
//            }
//        }
//    }
//
//    void bluetoothOff() {
//        if (mBluetoothAdapter.isEnabled()) {
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//            mBluetoothAdapter.disable();
//            Toast.makeText(getApplicationContext(), "블루투스가 비활성화 되었습니다.", Toast.LENGTH_SHORT).show();
//            mTvBluetoothStatus.setText("비활성화");
//        } else {
//            Toast.makeText(getApplicationContext(), "블루투스가 이미 비활성화 되어 있습니다.", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case BT_REQUEST_ENABLE:
//                if (resultCode == RESULT_OK) { // 블루투스 활성화를 확인을 클릭하였다면
//                    Toast.makeText(getApplicationContext(), "블루투스 활성화", Toast.LENGTH_LONG).show();
//                    mTvBluetoothStatus.setText("활성화");
//                } else if (resultCode == RESULT_CANCELED) { // 블루투스 활성화를 취소를 클릭하였다면
//                    Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_LONG).show();
//                    mTvBluetoothStatus.setText("비활성화");
//                }
//                break;
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    void listPairedDevices() {
//        if (mBluetoothAdapter.isEnabled()) {
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//            mPairedDevices = mBluetoothAdapter.getBondedDevices();
//
//            if (mPairedDevices.size() > 0) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("장치 선택");
//
//                mListPairedDevices = new ArrayList<String>();
//                for (BluetoothDevice device : mPairedDevices) {
//                    mListPairedDevices.add(device.getName());
//                    //mListPairedDevices.add(device.getName() + "\n" + device.getAddress());
//                }
//                final CharSequence[] items = mListPairedDevices.toArray(new CharSequence[mListPairedDevices.size()]);
//                mListPairedDevices.toArray(new CharSequence[mListPairedDevices.size()]);
//
//                builder.setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int item) {
//                        connectSelectedDevice(items[item].toString());
//                    }
//                });
//                AlertDialog alert = builder.create();
//                alert.show();
//            } else {
//                Toast.makeText(getApplicationContext(), "페어링된 장치가 없습니다.", Toast.LENGTH_LONG).show();
//            }
//        } else {
//            Toast.makeText(getApplicationContext(), "블루투스가 비활성화 되어 있습니다.", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    void connectSelectedDevice(String selectedDeviceName) {
//        for (BluetoothDevice tempDevice : mPairedDevices) {
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//            if (selectedDeviceName.equals(tempDevice.getName())) {
//                mBluetoothDevice = tempDevice;
//                break;
//            }
//        }
//        try {
//            mBluetoothSocket = mBluetoothDevice.createRfcommSocketToServiceRecord(BT_UUID);
//            mBluetoothSocket.connect();
//            mThreadConnectedBluetooth = new ConnectedBluetoothThread(mBluetoothSocket);
//            mThreadConnectedBluetooth.start();
//            mBluetoothHandler.obtainMessage(BT_CONNECTING_STATUS, 1, -1).sendToTarget();
//        } catch (IOException e) {
//            Toast.makeText(getApplicationContext(), "블루투스 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    private class ConnectedBluetoothThread extends Thread {
//        private final BluetoothSocket mmSocket;
//        private final InputStream mmInStream;
//        private final OutputStream mmOutStream;
//
//        public ConnectedBluetoothThread(BluetoothSocket socket) {
//            mmSocket = socket;
//            InputStream tmpIn = null;
//            OutputStream tmpOut = null;
//
//            try {
//                tmpIn = socket.getInputStream();
//                tmpOut = socket.getOutputStream();
//            } catch (IOException e) {
//                Toast.makeText(getApplicationContext(), "소켓 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
//            }
//
//            mmInStream = tmpIn;
//            mmOutStream = tmpOut;
//        }
//        public void run() {
//            byte[] buffer = new byte[1024];
//            int bytes;
//
//            while (true) {
//                try {
//                    bytes = mmInStream.available();
//                    if (bytes != 0) {
//                        SystemClock.sleep(100);
//                        bytes = mmInStream.available();
//                        bytes = mmInStream.read(buffer, 0, bytes);
//                        mBluetoothHandler.obtainMessage(BT_MESSAGE_READ, bytes, -1, buffer).sendToTarget();
//                    }
//                } catch (IOException e) {
//                    break;
//                }
//            }
//        }
//        public void write(String str) {
//            byte[] bytes = str.getBytes();
//            try {
//                mmOutStream.write(bytes);
//            } catch (IOException e) {
//                Toast.makeText(getApplicationContext(), "데이터 전송 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
//            }
//        }
//        public void cancel() {
//            try {
//                mmSocket.close();
//            } catch (IOException e) {
//                Toast.makeText(getApplicationContext(), "소켓 해제 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//}
//
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.widget.Button;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import android.app.Activity;
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//import android.bluetooth.BluetoothSocket;
//import android.media.AudioManager;
//import android.view.View;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Set;
//import java.util.UUID;
//
//public class BluetoothMusic extends AppCompatActivity {
//    private int resId;
//    private Button btn_play;
//    private Button btn_stop;
//
//    MediaPlayer mediaPlayer;
//    private BluetoothAdapter bluetoothAdapter;
//    private BluetoothDevice bluetoothDevice;
//    private BluetoothSocket bluetoothSocket;
//    private InputStream inputStream;
//    private Button bluetoothButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.bluetooth_music);
////        Intent intent = getIntent();
////        String str = intent.getStringExtra("str");
//        bluetoothButton = findViewById(R.id.bluetoothButton);
//        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//
//        // Bluetooth 장치 선택 및 연결
//        connectToBluetoothDevice();
//        // 블루투스 스피커로 오디오 전송 버튼 클릭 이벤트 처리
//        bluetoothButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mediaPlayer.isPlaying()) {
//                    mediaPlayer.pause();
//                    bluetoothButton.setText("재생");
//                } else {
//                    mediaPlayer.start();
//                    bluetoothButton.setText("일시정지");
//                }
//            }
//        });
//    }
//
//    private void connectToBluetoothDevice() {
//        if (bluetoothAdapter == null) {
//            // 블루투스가 지원되지 않는 경우 처리
//            return;
//        }
//
//        if (!bluetoothAdapter.isEnabled()) {
//            // 블루투스가 비활성화된 경우, 활성화 요청을 사용자에게 보여줄 수 있습니다.
//            // 여기서는 활성화 요청을 생략하고 사용자에게 활성화하도록 요청하거나 설정 액티비티로 이동하는 코드를 추가할 수 있습니다.
//            return;
//        }
//
//        // 페어링된 블루투스 장치 목록 가져오기
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
//
//        if (pairedDevices.size() > 0) {
//            for (BluetoothDevice device : pairedDevices) {
//                // 여기에서 블루투스 스피커를 찾거나 필요한 블루투스 장치를 선택합니다.
//                if (device.getName().equals("Bluetooth_Speaker_Name")) {
//                    bluetoothDevice = device;
//                    break;
//                }
//            }
//
//            if (bluetoothDevice != null) {
//                // BluetoothSocket 초기화 및 연결
//                connectBluetoothSocket();
//            }
//        }
//    }
//
//    private void connectBluetoothSocket() {
//        try {
//            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // SPP 프로필 UUID
//
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
//            bluetoothSocket.connect();
//
//            inputStream = bluetoothSocket.getInputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // MediaPlayer 초기화 및 오디오 스트리밍 시작
//        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//
//        String str = "sample_music";
//
//        resId = getResources().getIdentifier(str, "raw", getPackageName());
//
//
//        try {
//            mediaPlayer = MediaPlayer.create(this, resId);
////            mediaPlayer.setDataSource(inputStream.getFD()); // setDataSource 안에 string으로 파일 경로 지정하여 사용
//            mediaPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        String str = "sample_music";
////
////        resId = getResources().getIdentifier(str, "raw", getPackageName());
//
////        if (resId != 0) {
////            mediaPlayer = MediaPlayer.create(this, resId);
////            mediaPlayer.start();
//
//
//        btn_play = (Button) findViewById(R.id.btn_play);
//        btn_play.setOnClickListener(v -> {
//            mediaPlayer = MediaPlayer.create(this, resId);
//            mediaPlayer.start();
//        });
//
//        btn_stop = (Button) findViewById(R.id.btn_stop);
//        btn_stop.setOnClickListener(v -> {
//            mediaPlayer.stop();
//        });
//    }
//}
