package cn.ding.hu.rpcclient;

import android.os.Bundle;
import android.util.Log;

import cn.ding.hu.androidipc.AndroidRpc;
import cn.ding.hu.androidipc.AndroidRpcActivty;
import cn.ding.hu.androidipc.IRpcInvokeListener;
import cn.ding.hu.androidipc.IRpcListener;
import cn.ding.hu.androidipclib.IRpcService;
import cn.ding.hu.androidipclib.User;

public class MainActivity extends AndroidRpcActivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidRpc.init(getApplication(),"cn.ding.hu.androidipclib");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidRpc.connect(this, new IRpcListener() {
            @Override
            public void onConnect() {
                //跨进程调用服务app的方法
                AndroidRpc.invoke(MainActivity.this, IRpcService.class, "getName",
                        new IRpcInvokeListener<String>() {
                            @Override
                            public void onResult(String s) {
                                Log.i("rpc"," MainActivity.this.result:"+ s);
                            }
                        },new Object[]{});

                AndroidRpc.invoke(MainActivity.this, IRpcService.class, "getCal",
                        new IRpcInvokeListener<Integer>() {
                            @Override
                            public void onResult(Integer s) {
                                Log.i("rpc"," getCal.result:"+ s);
                            }
                        },5,6);

                AndroidRpc.invoke(MainActivity.this, IRpcService.class, "getUser",
                        new IRpcInvokeListener<User>() {
                            @Override
                            public void onResult(User s) {
                                Log.i("rpc"," getCal.result:"+ s.name+s.age+s.high);
                            }
                        },new Object[]{});
            }
        });

    }
}