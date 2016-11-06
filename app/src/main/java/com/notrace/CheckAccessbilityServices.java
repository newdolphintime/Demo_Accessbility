package com.notrace;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.List;

/**
 * Created by notrace on 2016/8/5.
 */
public class CheckAccessbilityServices extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
      int type= event.getEventType();
        switch (type)
        {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:

                //切换页面的时候此时会触发一个叫TYPE_WINDOW_STATE_CHANGED的事件
                AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
                if(nodeInfo!=null)
                {
                    if("com.notrace".equals(event.getPackageName()))
                    {
                        if("com.notrace.MainActivity".equals(event.getClassName())){
                            List<AccessibilityNodeInfo> list=nodeInfo.findAccessibilityNodeInfosByViewId("com.notrace:id/btn_click");
                            if(list!=null&&list.size()>0)
                            {
                                list.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        }
                    }

                }
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
            break;
        }
    }

    @Override
    public void onInterrupt() {
        Log.d("=====", "clickservice中断");
        Toast.makeText(this, "中断点击", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("=====", "clickserviceonCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("=====", "clickserviceonDestroy");
    }
}
