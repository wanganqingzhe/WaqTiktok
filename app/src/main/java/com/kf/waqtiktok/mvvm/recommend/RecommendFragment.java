package com.kf.waqtiktok.mvvm.recommend;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.kf.waqtiktok.R;
import com.kf.waqtiktok.databinding.FragmentRecommendBinding;
import com.kf.waqtiktok.mvvm.adapter.CommentAdapter;
import com.kf.waqtiktok.mvvm.adapter.RecommendAdapter;
import com.kf.waqtiktok.mvvm.base.BaseFragment;
import com.kf.waqtiktok.mvvm.entity.CommentEntity;
import com.kf.waqtiktok.mvvm.entity.ConcernEntity;
import com.kf.waqtiktok.mvvm.entity.VideoEntity;
import com.kf.waqtiktok.mvvm.live.LiveActivity;
import com.kf.waqtiktok.mvvm.login.LoginActivity;
import com.kf.waqtiktok.utils.db.RoomDbUtils;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;


public class RecommendFragment extends BaseFragment<FragmentRecommendBinding, RecommendViewModel> {

    //短视频数据
    private List<VideoEntity.DataDTO> data = new ArrayList<>();
    //短视频适配器
    private RecommendAdapter adapter = new RecommendAdapter(data);
    //视频条目的位置
    private int firstVisibleItemPosition;
    //评论数据
    private List<CommentEntity.DataDTO> commentData = new ArrayList<>();
    //评论适配器
    private CommentAdapter commentAdapter = new CommentAdapter(R.layout.item_comment, commentData);


    @Override
    protected void initData() {

        //请求成功视频数据

        viewModel.videoSuccess.observe(this, videoEntity -> {

            //成功
            data.addAll(videoEntity.getData());
            adapter.notifyDataSetChanged();


        });
        //关注成功的处理
        viewModel.concernSuccess.observe(this, concernEntity -> {

            ToastUtils.showShort(concernEntity.getMessage());
            if (concernEntity.getCode() == 200) {

                //对+号做动画效果  把加号变成对号  然后播放属性动画 2 秒后消息
                ImageView byPosition = (ImageView) adapter.getViewByPosition(firstVisibleItemPosition, R.id.iv_guanzhu);
                byPosition.setImageResource(R.drawable.ic_duigou);

                //设置关注动画
                ObjectAnimator animator = ObjectAnimator.ofFloat(byPosition, "alpha", 1, 0);
                animator.setDuration(2000);
                animator.start();

                //修改Entity的状态
                data.get(firstVisibleItemPosition).setGuanzhu(1);


            }

        });
        //点赞成功
        viewModel.loveSuccess.observe(this, new Observer<ConcernEntity>() {
            @Override
            public void onChanged(ConcernEntity concernEntity) {

                //如果点赞成功
                if (concernEntity.getCode() == 200) {

                    //1.切换成红心图片
                    ImageView ivLove = (ImageView) adapter.getViewByPosition(firstVisibleItemPosition, R.id.iv_dianzan);
                    ivLove.setImageResource(R.drawable.ic_xihuanred);
                    //2.播放动画
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(ivLove, "scaleX", 1, 1.2f, 1);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(ivLove, "scaleY", 1, 1.2f, 1);
                    AnimatorSet set = new AnimatorSet();
                    set.setDuration(1000);
                    set.play(scaleX).with(scaleY);
                    set.start();
                    //修改视频实体类中点赞状态为1
                    data.get(firstVisibleItemPosition).setDianzan(1);

                    //显示点赞数量加1
                    TextView textView = (TextView) adapter.getViewByPosition(firstVisibleItemPosition, R.id.tv_dianzan);
                    textView.setText(data.get(firstVisibleItemPosition).getLike_count() + 1 + "");


                }


            }
        });
        //取消点赞成功
        viewModel.noLoveSuccess.observe(this, new Observer<ConcernEntity>() {
            @Override
            public void onChanged(ConcernEntity concernEntity) {

                ToastUtils.showShort(concernEntity.getMessage());
                if (concernEntity.getCode() == 200) {

                    //1.把图片变成 灰色
                    ImageView imageView = (ImageView) adapter.getViewByPosition(firstVisibleItemPosition, R.id.iv_dianzan);
                    imageView.setImageResource(R.drawable.ic_xihuan);
                    //2.修改实体类中 点赞状态为0
                    data.get(firstVisibleItemPosition).setDianzan(0);
                    //3.修改页面中  显示的点赞数量
                    TextView textView = (TextView) adapter.getViewByPosition(firstVisibleItemPosition, R.id.tv_dianzan);
                    textView.setText(data.get(firstVisibleItemPosition).getLike_count() + "");


                }


            }
        });

        //获取评论
        viewModel.commentSuccess.observe(this, new Observer<CommentEntity>() {
            @Override
            public void onChanged(CommentEntity commentEntity) {
                ToastUtils.showShort(commentEntity.getMessage());

                TextView textView = (TextView) adapter.getViewByPosition(firstVisibleItemPosition, R.id.tv_count);
                textView.setText("共" + commentEntity.getData().size() + "条评论");
                //展示评论列表
                RecyclerView recyclerView = (RecyclerView) adapter.getViewByPosition(firstVisibleItemPosition, R.id.rec_pinglun);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                //把评论数据添加到全局评论ArrayList中
                commentData.addAll(commentEntity.getData());
                recyclerView.setAdapter(commentAdapter);


            }
        });

        //发送评论评论
        viewModel.sendSuccess.observe(this, new Observer<CommentEntity>() {
            @Override
            public void onChanged(CommentEntity commentEntity) {

                ToastUtils.showShort(commentEntity.getMessage());
                if (commentEntity.getCode()==200){

                    //把新发表的评论 添加到评论的第一条
                    commentData.add(0,commentEntity.getData().get(0));
                    commentAdapter.notifyDataSetChanged();

                }



            }
        });



    }

    @Override
    protected void initView() {
        //设置布局

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        binding.rv.setLayoutManager(linearLayoutManager);
        //设置适配器
        binding.rv.setAdapter(adapter);
        //实现模仿抖音的翻页效果
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(binding.rv);


        binding.rv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {

                //找到第一个视频的位置
                firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                StandardGSYVideoPlayer videoPlayer = (StandardGSYVideoPlayer) adapter.getViewByPosition(firstVisibleItemPosition, R.id.gsy);
                videoPlayer.startPlayLogic();

            }
        });

        //给关注 点赞  评论  收藏  分享 图表  添加点击事件
        adapter.addChildClickViewIds(R.id.iv_guanzhu, R.id.iv_dianzan, R.id.iv_pinglun, R.id.iv_shoucang, R.id.iv_fenxiang,R.id.iv_close,R.id.iv_fasong,R.id.full,R.id.live);
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {


                //关注
                if (view.getId() == R.id.iv_guanzhu) {

                    concern();
                }
                //点赞
                if (view.getId() == R.id.iv_dianzan) {
                    love();
                }
                //评论
                if (view.getId() == R.id.iv_pinglun) {


                    //让短视频  RecycleView不拦截事假
                    binding.rv.setIntercept(false);
                    comment();
                }
                //关闭评论
                if (view.getId()==R.id.iv_close){
                    //让短视频  RecycleView不拦截事件
               binding.rv.setIntercept(true);
                    closeComment();
                }
                //发表评论
                if (view.getId()==R.id.iv_fasong){

                    sendComment();
                }
                //收藏
                if (view.getId() == R.id.iv_shoucang) {
                    like();
                }
                //分享
                if (view.getId() == R.id.iv_fenxiang) {
                    share();
                }
                if (view.getId()==R.id.full){
                    full();
                }
                //进入直播间
                if (view.getId()==R.id.live){

                    live();

                }


            }




        });


        //请求推荐视频数据
        viewModel.video(2, 15);


    }

    /**
     * 直播
     */
    private void live() {

        Intent intent = new Intent(getActivity(), LiveActivity.class);
        //传值  点击的视频
        intent.putExtra("data",data.get(firstVisibleItemPosition));
        startActivity(intent);

    }

    /**
     * 关注
     *
     * @param
     */
    private void concern() {
        ToastUtils.showShort("关注");

        //检测是否登录
        //判断本地sp中是否有token
        if (SPUtils.getInstance().getString("token").isEmpty()) {
            //如果为空跳转到登录页面
            startActivity(new Intent(getContext(), LoginActivity.class));
        }

        //2.调用 关注的业务
        viewModel.concern(data.get(firstVisibleItemPosition).getAuthname());


    }

    /**
     * 点赞
     *
     * @param position
     */

    long lastClickTime;

    private void love() {


        //判断本地sp中是否有 Token
        if (SPUtils.getInstance().getString("token").isEmpty()) {

            startActivity(new Intent(getContext(), LoginActivity.class));

        }
        long currentTimeMills = System.currentTimeMillis();
        if (currentTimeMills - lastClickTime < 5000) {
            ToastUtils.showShort("点击频繁");
            return;

        }

        lastClickTime = currentTimeMills;

        if (data.get(firstVisibleItemPosition).getDianzan() == 0) {
            //点赞
            viewModel.love(data.get(firstVisibleItemPosition).getId());
        } else {

            //取消点赞
            viewModel.noLove(data.get(firstVisibleItemPosition).getId());
        }


        ToastUtils.showShort("点赞");

    }

    /**
     * 展示评论
     * @param
     */
    private void comment() {

        LinearLayout layout = (LinearLayout) adapter.getViewByPosition(firstVisibleItemPosition, R.id.ll_pinglun);
        layout.setVisibility(View.VISIBLE);
        //请求数据评论
        viewModel.comment(data.get(firstVisibleItemPosition).getId());
        //设置入场动画
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.in);
        animation.setInterpolator(new BounceInterpolator());
        layout.startAnimation(animation);

    }

    /**
     * 关闭评论
     */
    private void closeComment() {

         //隐藏评论
        LinearLayout layout= (LinearLayout) adapter.getViewByPosition(firstVisibleItemPosition,R.id.ll_pinglun);
         layout.setVisibility(View.GONE);
         //出场动画
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.out);
        layout.startAnimation(animation);


    }

    /**
     * 发送评论
     */
    private void sendComment() {

        //1.获取输入框的内容
       EditText editText= (EditText) adapter.getViewByPosition(firstVisibleItemPosition,R.id.et_pinglun);
       //2.判断 输入框中是否有内容
        if (editText.getText().toString().isEmpty()){

            ToastUtils.showShort("您还没有输入任何内容 !");
            return;

        }   //3.调用发送评论的业务
        viewModel.sendCommend(data.get(firstVisibleItemPosition).getId(),editText.getText().toString());
        //4.清空输入框
        editText.getText().clear();

    }

    /**
     * 收藏
     * @param
     */
    private void like() {
       // ToastUtils.showShort("收藏");
       //1.判断有没有收藏过
         ImageView ivLike= (ImageView) adapter.getViewByPosition(firstVisibleItemPosition,R.id.iv_shoucang);
        VideoEntity.DataDTO likeBean=  RoomDbUtils.getInstance().getVideoDao().queryById(data.get(firstVisibleItemPosition).getId());
         if (likeBean==null){
             //把点击的视频数据添加到数据库
             RoomDbUtils.getInstance().getVideoDao().insertVideo(data.get(firstVisibleItemPosition));
             //提示收藏成功
             ToastUtils.showShort("收藏成功");
             //修改图片颜色为灰色
             ivLike.setImageResource(R.drawable.ic_shoucangyellow);

         }else {

             //收藏过执行取消收藏
             //从数据库中删除
             RoomDbUtils.getInstance().getVideoDao().deleteVideo(likeBean);
             //提示取消收藏成功
             ToastUtils.showShort("取消收藏成功");
             //修改图片颜色为灰色
             ivLike.setImageResource(R.drawable.ic_shoucang);


         }



    }

    /**
     * 分享
     *
     * @param
     */

    private void share() {
        ToastUtils.showShort("分享");
        //android 系统分享
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, data.get(firstVisibleItemPosition).getCaption() + "播放地址" + data.get(firstVisibleItemPosition).getVideopath());
        startActivity(Intent.createChooser(intent, "分享视频"));
    }

    /**
     *   全屏观看
     * @return
     */

    private void full() {

        StandardGSYVideoPlayer videoPlayer= (StandardGSYVideoPlayer) adapter.getViewByPosition(firstVisibleItemPosition,R.id.gsy);
        //是否横屏  全屏
      //  videoPlayer.setLockLand(true);
        videoPlayer.startWindowFullscreen(getActivity(),true,true);


    }





    @Override
    protected Class<RecommendViewModel> getViewModel() {
        return RecommendViewModel.class;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void onPause() {
        super.onPause();
        //暂停
        GSYVideoManager.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        //继续播放
        GSYVideoManager.onResume();

    }
}