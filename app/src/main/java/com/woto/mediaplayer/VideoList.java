package com.woto.mediaplayer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fangxubin on 2017/6/15.
 */

public class VideoList extends AppCompatActivity {
    private List<Video> videoList = new ArrayList<Video>();
    //private static final String TAG = "VideoList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.native_video_list);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        //初始化本地视频数据
        initVideo();

        VideoAdapter adapter = new VideoAdapter(VideoList.this,R.layout.video_file,videoList);
        final ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        //设置视频点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Video video = videoList.get(position);
                //点击视频进行观看
                Intent intent = new Intent(VideoList.this,NativeVideo.class);
                intent.putExtra("path",video.getPath());
                intent.putExtra("name",video.getName().substring(0,video.getName().lastIndexOf(".")));
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化视频数据
     */
    private void initVideo(){
        //获取sd卡路径
        File srcFolder = new File(Environment.getExternalStorageDirectory().getPath());
        List<File> list = new ArrayList<File>();
        findVideoFile(srcFolder,list);

        //设置视频数据
        for(int i=0;i<10;i++) {
            for (File file : list) {
                    Video _video = new Video(R.drawable.ic_action_video, file.getName(),file.getPath());
                    videoList.add(_video);
            }
        }
    }

    /**
     * 查找指定文件夹下的所有视频文件
     * @param srcFolder
     * @param list
     */
    private static void findVideoFile(File srcFolder, List<File> list) {
        // 获取该目录下的所有文件或者文件夹的File数组
        File[] fileArray = srcFolder.listFiles();

        if (fileArray != null) {
            // 遍历该File数组，得到每一个File对象
            for (File file : fileArray) {
                // 判断该File对象是否是文件夹
                if (file.isDirectory()) {
                    findVideoFile(file, list);
                } else {
                    //将文件名称转化为小写字母，方便判断
                    String filename = file.getName().toLowerCase();
                    if (filename.endsWith(".mp4") | filename.endsWith(".avi") | filename.endsWith(".mkv") |
                            filename.endsWith(".wmv") | filename.endsWith(".mpg") | filename.endsWith(".3gp") |
                            filename.endsWith(".webm") | filename.endsWith(".vob") | filename.endsWith(".mov") |
                            filename.endsWith(".flv") | filename.endsWith(".swf") | filename.endsWith(".gif") |
                            filename.endsWith(".ts")) {
                        list.add(file);
                    }
                }
            }
        }
    }
}