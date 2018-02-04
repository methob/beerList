package com.br.beerlist.beerlist.services

import android.content.Context
import com.br.beerlist.beerlist.di.Injector
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.kotlin.delete
import javax.inject.Inject


class DatabaseRealm {

    @Inject
    lateinit var context: Context

    init {
        Injector.component.inject(this)
    }

    fun getRealmInstance() : Realm{
        return Realm.getDefaultInstance()
    }

    fun initRealmConfiguration() {
        Realm.init(context)

        val realmConfiguration = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    fun <T : RealmObject> findAll(clazz: Class<T>): List<T> {
        return getRealmInstance().where(clazz).findAll()
    }

    fun remove(realmObject: RealmObject) {

        if (realmObject.isManaged) {

            val realm = getRealmInstance()

            if (!realm.isInTransaction) {
                realm.beginTransaction()
            }

            realmObject.deleteFromRealm()

            realm.commitTransaction()
            realm.close()
        }
    }

    fun getStandAloneObject(realmObject: RealmObject) :RealmObject  {

        val realm = getRealmInstance()
        return realm.copyFromRealm(realmObject)
    }

    fun addStandAloneObject(realmObject: RealmObject) {

        val realm = getRealmInstance()

        if (!realm.isInTransaction) {
            realm.beginTransaction()
        }

        realm.copyToRealm(realmObject)
        realm.commitTransaction()
        realm.close()
    }

    fun add(realmObject: RealmObject) : Boolean {

        val realm = getRealmInstance()

        if (!realm.isInTransaction) {
            realm.beginTransaction()
        }

        realm.copyToRealmOrUpdate(realmObject)
        realm.commitTransaction()
        realm.close()

        return true
    }

    fun add(realmObject: List<RealmObject>) : Boolean {

        val realm = Realm.getDefaultInstance()

        if (!realm.isInTransaction) {
            realm.beginTransaction()
        }

        realm.copyToRealmOrUpdate(realmObject)
        realm.commitTransaction()
        realm.close()

        return true
    }

}