package com.example.dennis.androidfinal;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.util.Log;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.Metadata;
import com.spotify.sdk.android.player.PlaybackState;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class MainActivity extends AppCompatActivity implements
        SpotifyPlayer.NotificationCallback, ConnectionStateCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    public static final Player.OperationCallback mOperationCallback = new Player.OperationCallback() {
        @Override
        public void onSuccess() {
            Log.d("Success", "yes");
        }

        @Override
        public void onError(Error error) {
            Log.d("ERROR:", "no");
        }
    };

    // TODO: Replace with your client ID
    private static final String CLIENT_ID = "23e2f84522894565bbf47954996f8a91";
    // TODO: Replace with your redirect URI
    private static final String REDIRECT_URI = "android-login://callback";


    public LocationManager mLocationManager;
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 11;
    public static boolean gpsBool = false;
    public static boolean gpsBool2 = false;
    public static float light_threshold = 10.0f;
    public static boolean day_bool = false;
    public static boolean night_bool = false;
    public static boolean alert = true;
    public boolean checkLight = false;

    public static Player mPlayer;
    private PlaybackState mCurrentPlaybackState;
    private TextView mMetaDataArtist, mMetaDataSong, mMetaDataAlbum;
    private Metadata mMetaData;
    public static final List<String> DAY_THEME = Arrays.asList(
            "spotify:track:0O53YcPBLyveU0jVYZpUgk",
            "spotify:track:5AXxiMXRRzdvPi3QzRDJfq",
            "spotify:track:6gXrEUzibufX9xYPk3HD5p",
            "spotify:track:3bbUkaQYGQHkx1TJi7gPSL",
            "spotify:track:3xGJuHvSxFJxxYlHj5BIoT",
            "spotify:track:1y5d3k9bewTlCQ4xbPXjhN",
            "spotify:track:3I7IGPFe0DoNXZI9JDYa5P",
            "spotify:track:0CpxIwmIOKHOIgxupmRMNx",
            "spotify:track:2aoo2jlRnM3A0NyLQqMN2f",
            "spotify:track:3ZOEytgrvLwQaqXreDs2Jx"

    );

    public static final List<String> NIGHT_THEME = Arrays.asList(
            "spotify:track:1OgyTG8JVzJghfAWm4gI3C",
            "spotify:track:2BSvC0GrY7Gh373BRDgQf9",
            "spotify:track:5HNCy40Ni5BZJFw1TKzRsC",
            "spotify:track:6kbSo13qvSJHBjuKdGBUbb",
            "spotify:track:6mFkJmJqdDVQ1REhVfGgd1",
            "spotify:track:3lOoadPNLAAeDaLEtOndqa",
            "spotify:track:5bDEA48Dxyxoc3K4Dt7yRE",
            "spotify:track:1UImyGhodlbYnnqAennkGb",
            "spotify:track:1NbwKmuZLbFWXYHNixiMcH",
            "spotify:track:1AKfLYTm4RqfLKqgQmv9V2"

    );

    public static final List<String> FAST_THEME = Arrays.asList(
            "spotify:track:1fy015PWkCjeiN0mEQ28gK",
            "spotify:track:1Q2fYlSdwuutWj3QplhY9q",
            "spotify:track:3uJpLFPywddM2hhqgcgzfN",
            "spotify:track:2G1xOn9PhRgi63XWp2ToZx",
            "spotify:track:14EORgkbXqIx5K4Haucmnb",
            "spotify:track:278jOl7gFMJ9WMnUbGxWRe",
            "spotify:track:5PGSwTLoqCGt5bYhQZBFlw",
            "spotify:track:0lP4HYLmvowOKdsQ7CVkuq",
            "spotify:track:51c94ac31swyDQj9B3Lzs3",
            "spotify:track:6RJdYpFQwLyNfDc5FbjkgV"

    );

    public static final List<String> SLOW_THEME = Arrays.asList(
            "spotify:track:4PVC260ho51c5A5Xz2FTwY",
            "spotify:track:1OMdL6Ut9yYWgq05eUKGuq",
            "spotify:track:5sFdMGaWXdSWYtS93KimeP",
            "spotify:track:6lhhVRjJJk2He59jAtOSsm",
            "spotify:track:6bbjsAJnozh2qYMS9lFQDz",
            "spotify:track:6AxNrCQko6Ftb7xThkU4Wv",
            "spotify:track:2mRiYprn1Ia7kVevhMvuRG",
            "spotify:track:2OuACwejkbRvb8vomGmak7",
            "spotify:track:6fD08OgJKEiviZ1R3h8SK8",
            "spotify:track:1AIu4EHzrN7GdpeKgigZ3G"

    );

    // Request code that will be used to verify if the result comes from correct activity
    // Can be any integer
    private static final int REQUEST_CODE = 1337;
    public static int tracker = 0;
    public static String current = "";

    // TextViews for Light Sensor testing
    TextView textlight_available, textlight_reading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton btn = (ImageButton)findViewById(R.id.play);
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
        mMetaDataAlbum = (TextView) findViewById(R.id.album);
        mMetaDataArtist = (TextView) findViewById(R.id.artist);
        mMetaDataSong = (TextView) findViewById(R.id.song);


        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    MY_PERMISSION_ACCESS_FINE_LOCATION );

        }

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10*1000,
                0, mLocationListener);


        SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor LightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (LightSensor != null) {
           // textlight_available.setText("Sensor.TYPE_LIGHT Available");
            sensorManager.registerListener(
                    LightSensorListener,
                    LightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL
            );
        }
    }

    public void getLight(View view) {
        checkLight = true;
    }

    // Event Listener for Light Sensor
    private final SensorEventListener LightSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT && checkLight) {
                //textlight_reading.setText("Light: " + sensorEvent.values[0]);
                checkLight = false;

                if (sensorEvent.values[0] <= light_threshold && !night_bool) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                    builder1.setMessage("It seems like you're in a dark place. Would you like to play the Night Theme?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    night_bool = true;
                                    day_bool = false;
                                    gpsBool = false;
                                    gpsBool2 = false;
                                    alert = true;
                                    ImageButton btn = (ImageButton)findViewById(R.id.play);
                                    btn.setImageResource(R.drawable.pause);
                                    Random rn = new Random();
                                    int index = rn.nextInt(10);
                                    mPlayer.playUri(mOperationCallback, NIGHT_THEME.get(index), 0, 0);
                                    tracker = index;
                                    current = "night";
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    alert = true;
                                    dialog.cancel();
                                }
                            });

                    if (alert) {
                        alert = false;
                        AlertDialog alert11 = builder1.create();
                        alert11.show();

                    }
                } else if (sensorEvent.values[0] > light_threshold && !day_bool) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                    builder1.setMessage("It seems like you're in a light place. Would you like to play the Day Theme?");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    day_bool = true;
                                    night_bool = false;
                                    gpsBool = false;
                                    gpsBool2 = false;
                                    alert = true;
                                    ImageButton btn = (ImageButton)findViewById(R.id.play);
                                    btn.setImageResource(R.drawable.pause);
                                    Random rn = new Random();
                                    int index = rn.nextInt(10);
                                    mPlayer.playUri(mOperationCallback, DAY_THEME.get(index), 0, 0);
                                    tracker = index;
                                    current = "day";
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    alert = true;
                                    dialog.cancel();
                                }
                            });
                    if (alert) {
                        alert = false;
                        AlertDialog alert11 = builder1.create();
                        alert11.show();

                    }

                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
            // auto generated stub
        }
    };

    private final LocationListener mLocationListener = new LocationListener() {
        private Location mLastLocation;
        @Override
        public void onLocationChanged(Location location) {
            double speed = 0;
            if (this.mLastLocation != null) {
                speed = Math.sqrt(Math.pow(location.getLongitude() - mLastLocation.getLongitude(), 2)
                                + Math.pow(location.getLatitude() - mLastLocation.getLatitude(), 2)
                ) / (location.getTime() - this.mLastLocation.getTime());
            }
            if (location.hasSpeed()) {
                speed = location.getSpeed();
            }

            this.mLastLocation = location;


            if (speed > 1 && !gpsBool) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("It seems like you're moving quickly. Would you like to play the Fast Theme?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                gpsBool = true;
                                day_bool = false;
                                night_bool = false;
                                gpsBool2 = false;
                                ImageButton btn = (ImageButton)findViewById(R.id.play);
                                btn.setImageResource(R.drawable.pause);
                                Random rn = new Random();
                                int index = rn.nextInt(10);
                                mPlayer.playUri(mOperationCallback, FAST_THEME.get(index), 0, 0);
                                tracker = index;
                                current = "fast";
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            } else if (speed <= 1 && !gpsBool2){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("It seems like you're relaxing. Would you like to play the Slow Theme?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                gpsBool2 = true;
                                day_bool = false;
                                night_bool = false;
                                gpsBool = false;
                                ImageButton btn = (ImageButton)findViewById(R.id.play);
                                btn.setImageResource(R.drawable.pause);
                                Random rn = new Random();
                                int index = rn.nextInt(10);
                                mPlayer.playUri(mOperationCallback, SLOW_THEME.get(index), 0, 0);
                                tracker = index;
                                current = "slow";
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            System.out.println("onStatusChanged");
        }

        @Override
        public void onProviderEnabled(String provider) {
            System.out.println("onProviderEnabled");
        }

        @Override
        public void onProviderDisabled(String provider) {
            System.out.println("onProviderDisabled");
            //turns off gps services
        }
    };

    @Override
    protected void onDestroy() {
        Spotify.destroyPlayer(this);
        super.onDestroy();
    }

    @Override
    public void onPlaybackEvent(PlayerEvent event) {

        Log.d("Event: " , "event");
        mCurrentPlaybackState = mPlayer.getPlaybackState();
        ImageButton btn = (ImageButton)findViewById(R.id.play);
        if (mCurrentPlaybackState != null && mCurrentPlaybackState.isPlaying) {
            btn.setImageResource(R.drawable.pause);
        } else {
            btn.setImageResource(R.drawable.play);
        }
        mMetaData = mPlayer.getMetadata();
        if (mMetaData != null && mMetaData.currentTrack != null) {
            mMetaDataArtist.setText(mMetaData.currentTrack.artistName);
            mMetaDataAlbum.setText(mMetaData.currentTrack.albumName);
            mMetaDataSong.setText(mMetaData.currentTrack.name);
        }


    }

    @Override
    public void onPlaybackError(Error error) {
        Log.d("MainActivity", "Playback error received: " + error.name());
        switch (error) {
            // Handle error type as necessary
            default:
                break;
        }
    }

    @Override
    public void onLoggedIn() {
        Log.d("MainActivity", "User logged in");
    }

    @Override
    public void onLoggedOut() {
        Log.d("MainActivity", "User logged out");
    }

    @Override
    public void onLoginFailed(int i) {
        Log.d("MainActivity", "Login failed");
    }

    @Override
    public void onTemporaryError() {
        Log.d("MainActivity", "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.d("MainActivity", "Received connection message: " + message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                Config playerConfig = new Config(this, response.getAccessToken(), CLIENT_ID);
                Spotify.getPlayer(playerConfig, this, new SpotifyPlayer.InitializationObserver() {
                    @Override
                    public void onInitialized(SpotifyPlayer spotifyPlayer) {
                        mPlayer = spotifyPlayer;
                        mPlayer.addConnectionStateCallback(MainActivity.this);
                        mPlayer.addNotificationCallback(MainActivity.this);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
                    }
                });
            }
        }
    }

    public void showDayInfo(View view) {
        Intent intent = new Intent(view.getContext(), DayInfo.class);
        startActivity(intent);

    }

    public void showNightInfo(View view) {
        Intent intent = new Intent(view.getContext(), NightInfo.class);
        startActivity(intent);

    }

    public void showFastInfo(View view) {
        Intent intent = new Intent(view.getContext(), FastInfo.class);
        startActivity(intent);

    }

    public void showSlowInfo(View view) {
        Intent intent = new Intent(view.getContext(), SlowInfo.class);
        startActivity(intent);

    }

    public void onPauseButtonClicked(View view) {
        ImageButton btn = (ImageButton)findViewById(R.id.play);
        if (mCurrentPlaybackState != null && mCurrentPlaybackState.isPlaying) {
            mPlayer.pause(mOperationCallback);
            btn.setImageResource(R.drawable.play);
            Log.d("pause", "pausing");
        } else {
            mPlayer.resume(mOperationCallback);
            btn.setImageResource(R.drawable.pause);
            Log.d("resume", "resuming");
        }
    }

    public void playDay(View view) {
        ImageButton btn = (ImageButton)findViewById(R.id.play);
        btn.setImageResource(R.drawable.pause);
        Random rn = new Random();
        int index = rn.nextInt(10);
        mPlayer.playUri(mOperationCallback, DAY_THEME.get(index), 0, 0);
        tracker = index;
        current = "day";
        night_bool = false;
        day_bool = true;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playNight(View view) {
        ImageButton btn = (ImageButton)findViewById(R.id.play);
        btn.setImageResource(R.drawable.pause);
        Random rn = new Random();
        int index = rn.nextInt(10);
        mPlayer.playUri(mOperationCallback, NIGHT_THEME.get(index), 0, 0);
        tracker = index;
        current = "night";
        night_bool = true;
        day_bool = false;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playFast(View view) {
        ImageButton btn = (ImageButton)findViewById(R.id.play);
        btn.setImageResource(R.drawable.pause);
        Random rn = new Random();
        int index = rn.nextInt(10);
        mPlayer.playUri(mOperationCallback, FAST_THEME.get(index), 0, 0);
        tracker = index;
        current = "fast";
        gpsBool = true;
        day_bool = false;
        night_bool = false;
        gpsBool2 = false;
    }

    public void playSlow(View view) {
        ImageButton btn = (ImageButton)findViewById(R.id.play);
        btn.setImageResource(R.drawable.pause);
        Random rn = new Random();
        int index = rn.nextInt(10);
        mPlayer.playUri(mOperationCallback, SLOW_THEME.get(index), 0, 0);
        tracker = index;
        current = "slow";
        gpsBool2 = true;
        day_bool = false;
        night_bool = false;
        gpsBool = false;
    }

    public void skip(View view) {
        ImageButton btn = (ImageButton)findViewById(R.id.play);
        btn.setImageResource(R.drawable.pause);
        if (current.equals("day")) {
            tracker++;
            if (tracker == 10) {
                tracker = 0;
            }
            mPlayer.playUri(mOperationCallback, DAY_THEME.get(tracker), 0, 0);
        }

        if (current.equals("night")) {
            tracker++;
            if (tracker == 10) {
                tracker = 0;
            }
            mPlayer.playUri(mOperationCallback, NIGHT_THEME.get(tracker), 0, 0);
        }

        if (current.equals("fast")) {
            tracker++;
            if (tracker == 10) {
                tracker = 0;
            }
            mPlayer.playUri(mOperationCallback, FAST_THEME.get(tracker), 0, 0);
        }

        if (current.equals("slow")) {
            tracker++;
            if (tracker == 10) {
                tracker = 0;
            }
            mPlayer.playUri(mOperationCallback, SLOW_THEME.get(tracker), 0, 0);
        }
    }

    public void rewind(View view) {
        ImageButton btn = (ImageButton)findViewById(R.id.play);
        btn.setImageResource(R.drawable.pause);
        if (current.equals("day")) {
            tracker--;
            if (tracker == -1) {
                tracker = 9;
            }
            mPlayer.playUri(mOperationCallback, DAY_THEME.get(tracker), 0, 0);
        }

        if (current.equals("night")) {
            tracker--;
            if (tracker == -1) {
                tracker = 9;
            }
            mPlayer.playUri(mOperationCallback, NIGHT_THEME.get(tracker), 0, 0);
        }

        if (current.equals("fast")) {
            tracker++;
            if (tracker == -1) {
                tracker = 9;
            }
            mPlayer.playUri(mOperationCallback, FAST_THEME.get(tracker), 0, 0);
        }

        if (current.equals("slow")) {
            tracker++;
            if (tracker == -1) {
                tracker = 9;
            }
            mPlayer.playUri(mOperationCallback, SLOW_THEME.get(tracker), 0, 0);
        }


    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

}
