package com.dzl.groovy

import java.util.HashMap;
import java.util.Map;


"""
private RecyclerViewWrap rcl_seller;
private SwipeRefreshLayout swipe_layout;
private RelativeLayout view_load;

@SuppressWarnings("rawtypes")
private Adapter adapter_wrap;
private List<Seller> datas;

private View footer_view;
private int footer_params_width = 0;	//底部宽度
private int footer_params_height = 0;	//底部高度
private int footer_height = 0;			//正常高度  _320px
private LinearLayoutManager layout_vertical;
private int lastVisibleItem;
private int page = 0;

//onCreate() initView()
footer_height = (int) getResources().getDimension(R.dimen._320px);

rcl_seller = (RecyclerViewWrap) findViewById(R.id.rcl_seller);
swipe_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
view_load = (RelativeLayout) findViewById(R.id.view_load);


layout_vertical = new LinearLayoutManager(this);
layout_vertical.setOrientation(LinearLayoutManager.VERTICAL);
rcl_seller.setLayoutManager(layout_vertical);

datas = new ArrayList<Seller>();
SellerAdapter adapter_seller = new SellerAdapter(this, datas);
adapter_seller.setOnItemClickListener(new OnItemClickListener() {

	@Override
	public void onItemClick(View itemView, int position) {
		
	}
});
		
rcl_seller.setAdapter(adapter_seller);
adapter_wrap = rcl_seller.getAdapter();

swipe_layout.setOnRefreshListener(this);
rcl_seller.setOnScrollListener(new OnScrollListener() {
	
	
	@Override
	public void onScrollStateChanged(int newState) {
		
		if (newState == RecyclerView.SCROLL_STATE_IDLE) {
						
			lastVisibleItem = layout_vertical.findLastVisibleItemPosition();
			
			int size = datas.size();
			
			if (!isLastPage && size > 0 && lastVisibleItem >= size) {
				sellerPresenter.reqData(URL_TYPE_LIST, Constants.LOAD_MORE);
			}
			swipe_layout.setEnabled(size > 0 && layout_vertical.findFirstCompletelyVisibleItemPosition() == 0);
		}
		super.onScrollStateChanged(recyclerView, newState);

	}
});


@Override
public void onErrer(VolleyError error, int url_type, int load_type) {
	if (url_type == SELLER_LIST) {
		addFooterView(LOAD_ERROR,MATCH_PARENT, datas.isEmpty() ? MATCH_PARENT : footer_height, VolleyErrorHelper.getErrorMessage(error));
	}
}


@Override
public void showLoadingUI(int url_type, int load_type) {
	if (url_type == URL_TYPE_LIST) {
			
		if (load_type == Constants.LOAD_AUTO) {
			setViewVisible(view_load, V);
			
		}else if (load_type == Constants.LOAD_TOP) {
			swipe_layout.setRefreshing(true);
			
		}else if (load_type == Constants.LOAD_MORE) {
			addFooterView(FooterStatusView.LOAD_MORE, MATCH_PARENT, footer_height, null);
		}
	}
}

@Override
public void hideLoadingUI(int url_type, int load_type, boolean success) {
	
	if (url_type == SELLER_LIST) {
		
		if (load_type == Constants.LOAD_AUTO) {
			setViewVisible(view_load, G);
		}else if (load_type == Constants.LOAD_TOP) {
			if (swipe_layout.isRefreshing()) {
				swipe_layout.setRefreshing(false);
			}
		}
		
	}
	
}

@Override
public void onSuccessSeller(List<Seller> sellers, int url_type, int load_type) {
	
	if (load_type == Constants.LOAD_AUTO || load_type == Constants.LOAD_TOP) {
        datas.clear();
        page = 0;
    }

	int size = 0;
    if (sellers != null){
    	page++;
        size = sellers.size();
        datas.addAll(sellers);
    }
    
    if (datas.isEmpty()) {
    	
    	isLastPage = false;
    	swipe_layout.setEnabled(false);
    	
    	addFooterView(LOAD_EMPTY,MATCH_PARENT, MATCH_PARENT, "~~~~ 什么都没有喔 ~~~~");
    	
    } else {
    	
    	swipe_layout.setEnabled(true);
    	isLastPage = size < Constants.PAGE_SIZE;
    	
    	addFooterView(LOAD_END,MATCH_PARENT, footer_height , "~~~~ 到底儿 ~~~~");
    	
    }
	
	adapter_wrap.notifyDataSetChanged();
}


 private void addFooterView(int type, int width, int heigth, String end_error_msg) {

        if (heigth == MATCH_PARENT){
            heigth = rcl_seller.getMeasuredHeight();
        }

        if (footer_status_view == null) {
			footer_status_view = new FooterStatusView(activity);
			footer_status_view.init();
			footer_status_view.getImageView_empty().setBackgroundResource(R.drawable.default_image);
			
			footer_params_width = width;
			footer_params_height = heigth;

			RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(width, heigth);
			footer_status_view.setLayoutParams(params);
			rcl_home_goods.addFooterView(footer_status_view);

			footer_status_view.setOnEmptyViewClickListener(new OnEmptyViewClickListener() {

				@Override
				public void onEmptyClick(View v) {
					sellerPresenter.reqData(URL_TYPE_LIST, Constants.LOAD_AUTO);
				}
			});
			
			footer_status_view.setOnEndViewClickListener(new OnEndViewClickListener() {

				@Override
				public void onEndClick(View v) {

					if (datas.isEmpty()) {
						sellerPresenter.reqData(URL_TYPE_LIST, Constants.LOAD_AUTO);
					} else if (!isLastPage) {
						sellerPresenter.reqData(URL_TYPE_LIST, Constants.LOAD_MORE);
					}
				}
			});

		}

		if (width != footer_params_width || heigth != footer_params_height) {

			footer_params_width = width;
			footer_params_height = heigth;

			ViewGroup.LayoutParams params = footer_status_view.getLayoutParams();
			params.width = width;
			params.height = heigth;

		}

		footer_status_view.setViewStatusChange(type, end_error_msg);
    }

@Override
public void onRefresh() {
	sellerPresenter.reqData(SELLER_LIST, Constants.LOAD_TOP);
}

@Override
public Map<String, String> getParams(int url_type, int load_type) {
		
	Map<String, String> params = new HashMap<String, String>();
	if (url_type == URL_TYPE_LIST) {
		
		params.put("page", load_type == Constants.LOAD_AUTO || load_type == Constants.LOAD_TOP ? "1" : "" + (page + 1));
		params.put("size", Constants.PAGE_SIZE + "");
		
	}
	
	return params;
}

"""