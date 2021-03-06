package com.zuccessful.trueharmony.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.firebase.firestore.FirebaseFirestore;
import com.zuccessful.trueharmony.R;
import com.zuccessful.trueharmony.application.SakshamApp;
import com.zuccessful.trueharmony.fragments.BeginAgainFragment;
import com.zuccessful.trueharmony.utilities.Utilities;

import java.text.SimpleDateFormat;

import static com.zuccessful.trueharmony.utilities.Utilities.changeLanguage;

public class BeginAgain extends AppCompatActivity {
    long startTime;
    long endTime;
    String TAG = "BeginAgainModule";

    // private long oldtimespend;
    private SakshamApp app = SakshamApp.getInstance();
    private FirebaseFirestore db = app.getFirebaseDatabaseInstance();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:

            /*       final ArrayList<WeeklyTask> tasks=new ArrayList<WeeklyTask>();
                   tasks.clear();
                    if(Utilities.isInternetOn(BeginAgain.this)) {
                        db.collection("alarms/" + app.getAppUser(null).getId() + "/weekly_tasks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    Log.d("logss weekly", "success");
                                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                        WeeklyTask weekly_task = snapshot.toObject(WeeklyTask.class);
                                        tasks.add(weekly_task);

                                    }
                                }
                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                    }
                                });

                    }


                    Log.v("tasks______",tasks.size()+"");
                    Utilities.saveListOfWeeklyTasks(tasks);*/

//                    fragmentTransaction.replace(R.id.fragment_content, new MeasurementFragment()).commit();
                    BeginAgainFragment medFrag = new BeginAgainFragment();
                    fragmentTransaction.replace(R.id.fragment_content, medFrag).commit();
                    return true;
                case R.id.navigation_info:
                    Intent pIntent = new Intent(BeginAgain.this, PDFRenderActivity.class);
                    pIntent.putExtra("filename","self_reliance.pdf");
                    pIntent.putExtra("filename_hindi","self_reliance_hindi.pdf");

                    startActivity(pIntent);
                    break;
                case R.id.navigation_progress:
                    Intent i=new Intent(BeginAgain.this,WeeklyReport.class);
                    startActivity(i);
                     return true;
     /*           case R.id.navigation_info:

*//*
                    PdfRenderFragment p= new PdfRenderFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("filename", "medical_adherence.pdf");
                    p.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragment_content, p).commit();
                    return true;*//*
                case R.id.navigation_progress:
                    fragmentTransaction.replace(R.id.fragment_content, new MedStatsFrag()).commit();
                    return true;*/
        /*        case R.id.navigation_sarthi:
                    Bundle b1=new Bundle();
                    b1.putString("saarthi",getApplicationContext().getString(R.string.med_saarthi));
                    SarthiFrag f = new SarthiFrag();
                    f.setArguments(b1);
                    fragmentTransaction.replace(R.id.fragment_content, f).commit();
                    return true;*/
            }
            return false;
        }
    };

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Utilities.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startTime = System.currentTimeMillis();

        changeLanguage(getApplicationContext());


        setContentView(R.layout.
                activity_beginagain);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_daily_task));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content, new BeginAgainFragment()).commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private interface  FirestroreCallBack{
        void onCallBack(Long time);
    }

    protected void fetchObject(final FirestroreCallBack firestroreCallBack){



        SimpleDateFormat sdf = Utilities.getSimpleDateFormat();

//        final DocumentReference documentReference;
//        documentReference = db.collection("time_spent/")
//                .document(app.getAppUser(null)
//                        .getId())
//                .collection(sdf.format(new Date())).document("Medication");
//
//        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if(task.isSuccessful()){
//                    DocumentSnapshot documentSnapshot=task.getResult();
//                    Activity_Usage activity_usage = documentSnapshot.toObject(Activity_Usage.class);
//                    if(activity_usage==null){
//                        long time = 0;
//                        firestroreCallBack.onCallBack(time);
//                        return;
//                    }
//                    Log.d(TAG, "oldtime on database : " + Long.toString(activity_usage.getTime()));
//                    firestroreCallBack.onCallBack(activity_usage.getTime());
//
//                }else{
//                    Log.d(TAG,"Error: Can't get Activity_Usage",task.getException());
//                }
//
//            }
//
//        });

    }
    @Override
    protected void onStop()
    {
        endTime = System.currentTimeMillis();
        final long timeSpend = endTime - startTime;
        //update time
//        fetchObject(new FirestroreCallBack() {
//            @Override
//            public void onCallBack(Long totaltime) {
//                totaltime += timeSpend;
//                //change format of time
//                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(totaltime),
//                        TimeUnit.MILLISECONDS.toMinutes(totaltime) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(totaltime)),
//                        TimeUnit.MILLISECONDS.toSeconds(totaltime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totaltime)));
//
//
//                //Log.d(TAG, "OLDTIME " + Long.toString(oldtimespend));
//
//                //create object
//                Activity_Usage au = new Activity_Usage(hms,totaltime,"Medication");
//                SimpleDateFormat sdf = Utilities.getSimpleDateFormat();
//
//                //add to database
//                try {db.collection("time_spent/")
//                        .document(app.getAppUser(null)
//                                .getId())
//                        .collection(sdf.format(new Date()))
//                        .document("Medication")
//                        .set(au);
//                    Map<String, Object> today_date = new HashMap<>();
//                    SimpleDateFormat sdf2 = Utilities.getSimpleDateFormat();
//                    today_date.put(sdf2.format(new Date()), sdf2.format(new Date()));
//                    db.collection("time_dates/"+app.getPatientID()+"/dates").document("dates").set(today_date, SetOptions.merge());
//                    Map<String, Object> module_name = new HashMap<>();
//                    module_name.put(au.getActivity_name(), au.getActivity_name());
//                    db.collection("time_dates/"+app.getPatientID()+"/dates").document("module").set(module_name, SetOptions.merge());
//                } catch (Exception e) { Log.d(TAG ,"ERROR : can't get object", e); }
//
//            }
//        });





        super.onStop();
    }}