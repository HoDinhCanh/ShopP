package com.store.shopp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_in_up.*
import kotlinx.android.synthetic.main.item_row.*
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,MoviesAdapter.OnItemClickListener {
    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }
    private var movieList = ArrayList<MovieModel>()
    private lateinit var moviesAdapter: MoviesAdapter
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
//    lateinit var mRecyclerView : ListView
//    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database = FirebaseDatabase.getInstance().reference
        var getdata=object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb=StringBuilder()
                val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                moviesAdapter = MoviesAdapter(movieList,this@MainActivity)
                val layoutManager = LinearLayoutManager(applicationContext)
                recyclerView.layoutManager = layoutManager
                recyclerView.itemAnimator = DefaultItemAnimator()
                recyclerView.adapter = moviesAdapter
                for(i in snapshot.children){
//                    var movie = MovieModel(i.child("name").getValue().toString(),
//                        i.child("price").getValue().toString(),i.child("detail").getValue().toString(),
//                        i.child("color").getValue().toString())
                    movieList.add(MovieModel(i.child("name").getValue().toString(),
                        i.child("price").getValue().toString(),i.child("detail").getValue().toString(),
                        i.child("color").getValue().toString(),i.child("image").getValue().toString()))
                        Log.d("TEST",i.child("image").getValue().toString())
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)





        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        callFragment(HomeFragment() , "Store")
        toolbar_menu.setOnClickListener{
            drawerLayout.openDrawer(Gravity.LEFT)
        }
        navigationView.setNavigationItemSelectedListener(this)

        //xuat



    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_profile -> {
                Toast.makeText(this , "Profile clicked" , Toast.LENGTH_SHORT).show()
            }
            R.id.nav_update -> {
                Toast.makeText(this , "Profile Update clicked" , Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_address -> {
                Toast.makeText(this , "Cart clicked" , Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, CartActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_signout -> {
                Toast.makeText(this , "SignOut clicked" , Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, SignInUpActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun callFragment(fragment: Fragment, fragmentName: String){
        toolbar_title.text = fragmentName
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_fragment , fragment)
        transaction.commitAllowingStateLoss()
    }
    private fun getStore() {


    }

    override fun onclick(position: Int) {
        var intet=Intent(this,DetailActivity::class.java)
        intent.putExtra(INTENT_PARCELABLE,movieList[position])
        startActivity(intet)
    }
}