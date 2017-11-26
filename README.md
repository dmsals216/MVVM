# MVVM

## 1. MVVM이란

1. Model - data가 담겨있는 모델이라고 생각하면 쉽다.
2. View - 화면으로 xml파일이라고 생각하면 쉽다.
3. View Model - 위의 Model과 View를 연결해주는 Model이다.

## 2. MVVM 구현 - Use DataBinding

1. DabaBinding을 사용하기 위해 library를 다운로드 - 앱모듈의 build.gradle에 databinding요소를 추가
````
android {
    ....
    dataBinding {
        enabled = true
    }   
}
````
2. 2-1. 해당하는 액티비티의 Layout에 layout 루트태그로 감싼다.
````
<layout>
    <android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context="com.project.study.mvvm.MainActivity">

    </android.support.constraint.ConstraintLayout>
</layout>
````
2. 2-2. 레이아웃 안에 들어갈 데이터 클래스를 만든다.
````
public class User {
    public String id;
    public String password;
}
````
2. 2-3. 레이아웃 안에 데이터를 넣는다.
````
<?xml version="1.0" encoding="utf-8"?>
<layout>
    <data>
        <variable name="user" type="com.project.study.mvvm.User"/>
    </data>
    <android.support.constraint.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context="com.project.study.mvvm.MainActivity">

    </android.support.constraint.ConstraintLayout>
</layout>
````
3. 레이아웃 안에 바인딩할 데이터를 넣을 뷰를 넣고 @{}을 이용해 데이터를 세팅해준다.
````
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">
    <data>
        <variable name="user" type="com.project.study.mvvm.User"/>
    </data>
    <android.support.constraint.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context="com.project.study.mvvm.MainActivity">

        <TextView
            android:id="@+id/textID"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_marginEnd="8dp"
            android:layout_marginStart="8dp"
            android:layout_marginTop="8dp"
            android:gravity="center"
            android:textAppearance="@style/TextAppearance.AppCompat.Display1"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            android:text="@{user.id}"/>

        <TextView
            android:id="@+id/textPW"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_marginEnd="8dp"
            android:layout_marginStart="8dp"
            android:layout_marginTop="8dp"
            android:gravity="center"
            android:textAppearance="@style/TextAppearance.AppCompat.Display1"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/textID"
            android:text="@{user.password}"/>
    </android.support.constraint.ConstraintLayout>
</layout>
````
4. 바인딩이 완료된 클래스는 layout이름+Binding으로 자동 저장된다. Activity에서 바인딩을 시켜준다.
````
    ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    User user = new User();
    binding.setUser(user);
````
5. Activity안에서 데이터를 바꿔본다.
````
    user.id = "아이디";
    user.password = "비밀번호";
````

## List를 DataBinding 후 recyclerview에 넣어보기

1. 첫번째로는 layout을 만든다.
````
<?xml version="1.0" encoding="utf-8"?>
<layout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <android.support.constraint.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context="com.project.study.mvvm.ListActivity">

        <android.support.v7.widget.RecyclerView
            android:id="@+id/recyclerView"
            android:layout_width="368dp"
            android:layout_height="495dp"
            android:layout_marginBottom="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginStart="8dp"
            android:layout_marginTop="8dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />
    </android.support.constraint.ConstraintLayout>
</layout>
````
2. recycler안에 들어가는 viewitem을 만든다.
````
<?xml version="1.0" encoding="utf-8"?>
<layout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <data>
        <variable name="user" type="com.project.study.mvvm.User"/>
    </data>
    <android.support.constraint.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <TextView
            android:id="@+id/textid"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginEnd="8dp"
            android:layout_marginStart="8dp"
            android:layout_marginTop="8dp"
            android:text="@{user.id}"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

        <TextView
            android:id="@+id/textpw"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginBottom="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginStart="8dp"
            android:layout_marginTop="8dp"
            android:text="@{user.password}"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/textid" />
    </android.support.constraint.ConstraintLayout>
</layout>
````
3. Adapter와 Holder를 만드는데 이때 Holder에서 받아오는 view와 Holder에 바인딩을 시켜준다.
````
public class TestListAdapter extends RecyclerView.Adapter<TestListAdapter.Holder>{
    List<User> data = new ArrayList<>();

    public void setData(List<User> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        User user = data.get(position);
        holder.binding.setUser(user);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ListItemBinding binding;
        public Holder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
````
4. Activity에서 activity_list와 바인딩을 시켜준후 recyclerview에 데이터를 넣어준다.
````

    TestListAdapter adapter;
    ActivityListBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        adapter = new TestListAdapter();
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataSetting();
    }
    private void dataSetting() {
        String[] ids = {"dmsals", "merrong", "juwon", "haha", "zico"};
        String[] pws = {"fdsifjaisfdj", "foasdjfidjsiof", "sidajfoijsfoj","fijsaojfjsofasadf"};
        List<User> data = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            User user = new User();
            user.id = ids[i%5];
            user.password = pws[i%4];
            data.add(user);
        }
        adapter.setData(data);
    }
}
````


### 참고

http://gun0912.tistory.com/71