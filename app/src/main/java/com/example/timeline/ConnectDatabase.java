package com.example.timeline;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

// SQLITE DATABASE HAS BEEN USED SO WE HAVE TO EXTEND IT
public class ConnectDatabase extends SQLiteOpenHelper {


    // DATABASE NAME, TABLES AND ENTITIES ARE DECLARED IN THIS SECTION
    private static String databaseName = "Data";
    private static String databaseTable = "User";
    private static String postDatabase = "Post";

    private static String personId = "personId";
    private static String firstName = "firstName";
    private static String surname = "surname";
    private static String emailAddress = "email";
    private static String password = "password";
    private static String date_of_birth = "dob";
    private static String phoneNumber = "phoneNumber";
    private static String address = "address";
    private static String thumbnail = "thumbnail";
    private static String register_date= "register_date";
    private static String updateDate = "updateDate";

    private static String postId = "postId";
    private static String postDescription = "postDescription";
    private static String postDate = "postDate";
    private static String userId = "userId";

    private static int dbVersion = 1;       // DATABASE VERSION HAS BEEN SET


    // CONNECTION TO THE DATABASE "Data" HAS BEEN MADE
    public ConnectDatabase(@Nullable Context context) {
        super(context, databaseName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // QUERY TO CREATE TABLE "user"
        String query = "create table " + databaseTable + " ( " + personId + " INTEGER PRIMARY KEY AUTOINCREMENT , " + firstName + " TEXT, " + surname + " TEXT, " + emailAddress + " TEXT, " +
                password + " TEXT, " + date_of_birth + " TEXT, " + phoneNumber + " TEXT, " + register_date + " TEXT, " + updateDate + " TEXT, " + thumbnail + " TEXT, " + address + " TEXT)";

        // QUERY TO CREATE TABLE "Post"
        String postQuery = "create table "+ postDatabase + " ( " + postId + " INTEGER PRIMARY KEY AUTOINCREMENT , " + userId + " TEXT, " + firstName + " TEXT, "  +
                postDescription + " TEXT, " + postDate + " TEXT)" ;

        sqLiteDatabase.execSQL(query);  // EXECUTE THE DECLARED QUERY
        sqLiteDatabase.execSQL(postQuery);
    }

    // OVERRIDE METHOD TO DROP THE TABLE IN THE DATABASE IF THEY EXISTED ALREADY
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + databaseTable);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + postDatabase);
        onCreate(sqLiteDatabase);
    }

    // FUNCTION TO ADD USERS
    public void addIndividual(Person person) {
        // CONNECTION TO THE DATABASE
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();      // CONTENTVALUES ARE THERE TO STORE THE REPLACEABLE DATA OR ADD DATA FOR DATABASE
        // DATAS WERE ADDED TO THE "user" TABLE TAKING VALUES FROM person CLASS
        contentValues.put(firstName, person.getFirstName());
        contentValues.put(surname, person.getSurname());
        contentValues.put(password, person.getPassword());
        contentValues.put(emailAddress, person.getEmailAddress());
        contentValues.put(address, person.getAddress());
        contentValues.put(phoneNumber, person.getPhoneNumber());
        contentValues.put(date_of_birth, person.getDate_of_birth());
        contentValues.put(thumbnail, person.getThumbnail());
        contentValues.put(register_date,person.getRegisterDate());
        contentValues.put(updateDate,person.getUpdateDate());
        // IT INSERT THE CONTENTVALUES TO THE DATABASE
        database.insert(databaseTable, null, contentValues);
    }

    // TO UPDATE THE DATA OF USER
    public void updateProfile(Person person) {

        // GET CONNECTION TO THE DATABASE
        SQLiteDatabase database = this.getWritableDatabase();
        String id = person.getPersonId();
        //Log.i("Show value90090", id);

        // CONTENTVALUES TO STORE THE DATAS
        ContentValues contentValues = new ContentValues();
        contentValues.put(personId,person.getPersonId());   // IN personId ENTITY IT PASSES VALUE person.getPersonId()..
        contentValues.put(firstName, person.getFirstName());
        contentValues.put(surname, person.getSurname());
        contentValues.put(password, person.getPassword());
        contentValues.put(emailAddress, person.getEmailAddress());
        contentValues.put(address, person.getAddress());
        contentValues.put(phoneNumber, person.getPhoneNumber());
        contentValues.put(date_of_birth, person.getDate_of_birth());
        contentValues.put(thumbnail, person.getThumbnail());
        contentValues.put(register_date,person.getRegisterDate());
        contentValues.put(updateDate,person.getUpdateDate());
        // IT UPDATE THE DATABASE TABLE WHERE THE personId is similar to the logged person
        database.update(databaseTable, contentValues, personId + " = ?", new String[] {String.valueOf(id)});

    }

    // This method is to update the username in general timeline section after the user updates his profile name
    public void updatePostUserName(Post post){
        SQLiteDatabase database = this.getWritableDatabase();
        String id = post.getUserId();
        ContentValues contentValues = new ContentValues();
        contentValues.put(firstName,post.getName());

        // IT UPDATES THE NAME OF THE USER IN GENERAL TIMELINE SECTION
        database.update(postDatabase,contentValues,userId + " = ?", new String[] {String.valueOf(id)});

    }

    // THIS METHOD IS TO ADD THE POST BY USER IN GENERAL TIMELINE SECTION
    public void addPost(Post post){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(userId, post.getUserId());
        contentValues.put(firstName, post.getName());
        contentValues.put(postDescription, post.getDescription());
        contentValues.put(postDate,post.getDate());

        // INSERT QUERY HAS BEEN USED IN THIS MEHOD WHERE ALL THE CONTENTVALUES HAS BEEN PASSED
        database.insert(postDatabase, null, contentValues);
    }

    // TO SHOW ALL THE POST IN THE GENERAL TIMELINE SECTION PRESENTED IN DATABASE
    public Cursor readPost(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "Select * from Post order by postId desc";       // QUERY TO GET ALL THE POSTS
        Cursor cursor=database.rawQuery(query,null);
        return cursor;  // IT RETURNS THE CURSOR WHICH INCLUDE ALL THE POSTS
    }

    // TO SHOW ALL THE AVAILABLE USERS IN THE ADMINISTRATOR SECTION
    public Cursor readAllUser(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "Select * from User order by personId desc";
        Cursor cursor=database.rawQuery(query,null);
        return cursor;
    }

    // TO EDIT THE POST OF USER
    public void editPost(String post_id, String post_description){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(postId, post_id);
        contentValues.put(postDescription, post_description);

        // UPDATE QUERY HAS BEEN USED
        database.update(postDatabase,contentValues,postId + " = ?", new String[] {String.valueOf(post_id)});

    }

    // TO DELETE THE POST BY THE USER
    public void deletePost(String post_id){
        SQLiteDatabase database = this.getWritableDatabase();
        // DELETE THE POST WHERE THE POST ID MATCH
        database.delete(postDatabase,postId + " = ?",new String[]{post_id});
    }

    // WHEN ADMIN DELETE THE USER, ITS POST SHOULD ALSO DELETED
    public void deletePostByAdmin(String person_id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(postDatabase,userId + " = ?",new String[]{person_id});
    }

    // TO DELETE THE USER BY ADMINISTRATOR
    public void deleteUser(String person_id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(databaseTable,personId + " = ?",new String[]{person_id});
    }

    // TO READ ALL MY POSTS
    public Cursor readMyAllPost(String _userId){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "Select * from Post where userId = ? order by postId desc";
        Cursor cursor=database.rawQuery(query,new String[] {_userId});
        return cursor;
    }

    // TO CHECK FOR LOGIN, WHETHER THE USER REGISTERED OR NOT
    public Cursor checkSignUp(String _firstName, String _password) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM User WHERE firstName = ? and password =?", new String[]{_firstName, _password});
        return cursor;
    }
}
