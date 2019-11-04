package com.aries.learnrealm;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    //memyimpan data
    public void save(final MahasiswaModel mahasiswaModel) {
        realm.executeTransaction (new Realm.Transaction ( ) {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Log.e ("Create", "Database was created");
                    Number currentIdNum = realm.where (MahasiswaModel.class).max ("id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue ( ) + 1;
                    }
                    mahasiswaModel.setId (nextId);
                    MahasiswaModel model = realm.copyToRealm (mahasiswaModel);
                } else {
                    Log.e ("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    //untuk memanggil semua data
    public List<MahasiswaModel> getAllMahasiswa() {
        RealmResults<MahasiswaModel> results = realm.where (MahasiswaModel.class).findAll ( );
        return results;
    }

    //untuk meng-update data
    public void update(final Integer id, final Integer nim, final String nama) {
        realm.executeTransactionAsync (new Realm.Transaction ( ) {
            @Override
            public void execute(Realm realm) {
                MahasiswaModel model = realm.where (MahasiswaModel.class)
                        .equalTo ("id", id)
                        .findFirst ( );
                model.setNim (nim);
                model.setNama (nama);
            }
        }, new Realm.Transaction.OnSuccess ( ) {
            @Override
            public void onSuccess() {
                Log.e ("pppp", "onSuccess: Update Successfully");
            }

        }, new Realm.Transaction.OnError ( ) {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace ( );
            }
        });

    }

    // untuk menghapus data
    public void delete(Integer id) {
        final RealmResults<MahasiswaModel> model = realm.where (MahasiswaModel.class).equalTo ("id", id).findAll ( );
        realm.executeTransaction (new Realm.Transaction ( ) {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm (0);
            }
        });
    }

}
