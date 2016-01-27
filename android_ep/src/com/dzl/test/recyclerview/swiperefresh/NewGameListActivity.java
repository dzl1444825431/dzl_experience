//package com.dzl.test.recyclerview.swiperefresh;
//
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.GridView;
//import android.widget.Toast;
//import java.util.ArrayList;
//import java.util.List;
//
//public class NewGameListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
//
//    GridView mGvGame;
//    NewGameListAdapter mNewGameListAdapter;
//    List<GameInfo> mGameList;
//    SwipeRefreshLayout mSwipeLayout;
//    private int mPage = 1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_new_game_list);
//
//        mGameList = new ArrayList<>();
//        mNewGameListAdapter = new NewGameListAdapter(this, mGameList);
//        mGvGame.setAdapter(mNewGameListAdapter);
//        mSwipeLayout.setDirection(SwipeRefreshLayoutDirection.BOTH);
//        mSwipeLayout.setOnRefreshListener(this);
//        requestData(mPage);
//
//    }
//
//    private void requestData(final int page) {
//        RequestParams params = new RequestParams(this);
//        params.put("page", page);
//        params.put("limit", 12);
//        HttpRequest.post(Api.NEW_GAME, params, new MyBaseHttpRequestCallback<NewGameResponse>() {
//
//            @Override
//            public void onLogicSuccess(NewGameResponse newGameResponse) {
//                mPage = page + 1;
//                if (newGameResponse.getData() != null) {
//                    mGameList.addAll(newGameResponse.getData());
//                    mNewGameListAdapter.notifyDataSetChanged();
//                } else {
//                    Toast.makeText(getBaseContext(), newGameResponse.getMsg(), Toast.LENGTH_SHORT).show();
//                }
//
//                if (newGameResponse.getData() != null && newGameResponse.getData().size() > 0) {
//                    mSwipeLayout.setDirection(SwipeRefreshLayoutDirection.BOTH);
//                } else {
//                    mSwipeLayout.setDirection(SwipeRefreshLayoutDirection.TOP);
//                }
//            }
//
//            @Override
//            public void onLogicFailure(NewGameResponse newGameResponse) {
//                super.onLogicFailure(newGameResponse);
//                String msg = newGameResponse.getMsg();
//                if (StringUtils.isEmpty(msg)) {
//                    msg = "网络异常";
//                }
//                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(int errorCode, String msg) {
//                super.onFailure(errorCode, msg);
//                Toast.makeText(getBaseContext(), "网络异常", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override public void onFinish() {
//                super.onFinish();
//                mSwipeLayout.setRefreshing(false);
//            }
//        });
//    }
//
//    @Override
//    public void onRefresh(SwipeRefreshLayoutDirection direction) {
//        if ( direction == SwipeRefreshLayoutDirection.TOP ) {
//            requestData(1);
//        } else {
//            requestData(mPage);
//        }
//    }
//}
