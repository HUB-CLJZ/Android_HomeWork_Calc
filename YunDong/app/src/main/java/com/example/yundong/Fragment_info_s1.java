package com.example.yundong;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_info_s1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_info_s1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_info_s1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private WebView mWebview;

    public Fragment_info_s1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_info_s1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_info_s1 newInstance(String param1, String param2) {
        Fragment_info_s1 fragment = new Fragment_info_s1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fmroot = inflater.inflate(R.layout.fragment_info_s1, container, false);
        fmroot.findViewById(R.id.button_fmt_s1_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(Uri.parse("hello nihao from fragment_info_s1"));
            }
        });

        mWebview = fmroot.findViewById(R.id.fraginfos1_webview);
        // 设置与Js交互的权限
        mWebview.getSettings().setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        mWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);//此处view和mWebview引用的是同一WebView对象
                return true;//返回true由WebView打开，false调用外部浏览器
            }
        });
        //mWebview.loadUrl("https://www.baidu.com");

        String data = "<!DOCTYPE html><html><head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<title>Title</title>\n" +
                "</head>\n" +
                "<script> function AnddcallJS(){ alert(\"Android调用了JS的AnddcallJS()方法\"); } </script>" +
                "<body>\n" +
                "    <h1 id=\"h\">学习运动知识、科学锻炼</h1>\n" +
                "    <button onclick=\"confirm('你好confirm')\">调用confirm</button>" +
                "    <button onclick=\"alert('你好alert')\">调用alert</button>" +
                "    <button onclick=\"prompt('你好prompt')\">调用prompt</button><br>" +
                "    发EMail:<a href=\"mailto:yourself@qq.com\">yourself@qq.com</a><br>" +
                "    拨打:<a href=\"tel:10086\">10086</a><br>" +
                "    发送:<a href=\"geopoint:116.281588,39.866166\">我的位置</a><br>" +
                "    <img height=50 width=120 src=\"https://www.baidu.com/img/pc_1c6e30772d5e4103103bd460913332f9.png\"  alt=\"百度LOGO\" />" +
                "</body>\n" +
                "</html>\n";
        mWebview.loadData(data, "text/html; charset=utf-8", "utf-8");

        mWebview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                WebView.HitTestResult result = ((WebView) view).getHitTestResult();
                if(result != null){
                    switch (result.getType()){//根据类型做相应的处理
                        case WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE:
                            String imgUrl = result.getExtra();
                            Log.d("YDHL",imgUrl);
                            return true;
                        case WebView.HitTestResult.IMAGE_TYPE:
                            String imgUrl2 = result.getExtra();
                            Log.d("YDHL",imgUrl2);
                            return true;
                        case WebView.HitTestResult.EMAIL_TYPE:
                            String email = result.getExtra();
                            Log.d("YDHL",email);
                            return true;
                        case WebView.HitTestResult.PHONE_TYPE:
                            String phone = result.getExtra();
                            Log.d("YDHL",phone);
                            return true;
                        default:
                            Log.d("YDHL","类型="+result.getType()+": toString()="+result.toString());
                            break;
                    }
                }
                return true;
            }
        });

        // 将Java类对象映射到JS中，JS中用iamandroidhero引用使用该对象
        mWebview.addJavascriptInterface(new JavaClassMaptoJs(), "iamandroidhero");
        mWebview.setWebChromeClient(new MyWebChromeClient());

        return fmroot;
    }

    class MyWebChromeClient extends WebChromeClient{

        // 拦截JS的警告框，参数message是调用JS方法alert()时传入的参数
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            boolean flag = false;
            //根据url和message解析是否要拦截的目标对话框，如果是置flag=true
            if(flag){
                //根据message中的指示，做相关工作
                result.confirm();//相当于点击了弹窗的OK按钮
                return true;//返回true不再弹窗
            }
            return false;//弹JS窗
        }

        // 拦截JS的确认框，参数message是调用JS方法confirm()时传入的参数
        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            boolean flag = false;
            //根据url和message解析是否要拦截的目标对话框，如果是置flag=true
            if(flag){
                //根据message中的指示，做相关工作
                result.confirm();//相当于点击了弹窗的OK按钮
                //result.cancel();//相当于点击了弹窗的cancel按钮
                return true;//返回true不再弹窗
            }
            return false;//弹JS窗
        }
        // 拦截输入框，参数message是调用JS方法prompt()时传入的参数
        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {

            boolean flag = false;
            //根据url和message解析是否要拦截的目标对话框，如果是置flag=true
            if(flag){
                //根据message中的指示，做相关工作
                result.confirm("hello js!!!");//相当于输入了信息并点击了弹窗的OK按钮
                //result.cancel();//相当于点击了弹窗的cancel按钮
                return true;//返回true不再弹窗
            }
            return false;//弹JS窗
        }
    }

    //映射到JS的类
    class JavaClassMaptoJs extends Object {
        // 被JS调用的方法必须加入@JavascriptInterface注解
        @JavascriptInterface
        public void func_1(String msg) {
            Log.d("YDHL",msg);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        // 调用javascript的callJS()方法
        mWebview.loadUrl("javascript:AnddcallJS()");
        mWebview.evaluateJavascript("javascript:AnddcallJS()",
                new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        //参数value中存放返回的结果
                        Log.d("YDHL","JS return : "+value);
                    }
                });
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
