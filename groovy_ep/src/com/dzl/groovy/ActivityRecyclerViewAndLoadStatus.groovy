package com.dzl.groovy


"""
private RecyclerViewWrap rcl_seller;
private SwipeRefreshLayout swipe_layout;
private RelativeLayout view_load;

@SuppressWarnings("rawtypes")
private Adapter adapter_seller_wrap;

private List<Seller> datas;

private View footer_view;
private int footer_params_width = 0;	//底部宽度
private int footer_params_height = 0;	//底部高度
private int footer_height = 0;			//正常高度  _320px
private LinearLayoutManager layout_vertical;
private int lastVisibleItem;
private int page = 0;

private View layout_load_empty;
private View layout_load_loading;
private View layout_loadmore_end;
private TextView layout_load_end;
private TextView layout_load_error;

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
adapter_seller_wrap = rcl_seller.getAdapter();

swipe_layout.setOnRefreshListener(this);
rcl_seller.setOnScrollListener(new OnScrollListener() {
	
	@Override
	public void onScrolled(int dx, int dy) {
		
	}
	
	@Override
	public void onScrollStateChanged(int newState) {
		lastVisibleItem = layout_vertical.findLastVisibleItemPosition();
        if (!isLastPage && newState == RecyclerView.SCROLL_STATE_IDLE
                && datas.size() > 0 && lastVisibleItem >= datas.size()) {
            sellerPresenter.reqData(SELLER_LIST, Constants.LOAD_MORE);
        }
        swipe_layout.setEnabled(datas.size() > 0 ? layout_vertical.findFirstCompletelyVisibleItemPosition() == 0 : false);

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
	if (url_type == SELLER_LIST && load_type == Constants.LOAD_AUTO) {
		setViewVisible(view_load, V);
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
	
	adapter_seller_wrap.notifyDataSetChanged();
}


 private void addFooterView(int type, int width, int heigth, String end_error_msg) {

        if (heigth == MATCH_PARENT){
            heigth = rcl_seller.getMeasuredHeight();
        }

        if (footer_view == null) {
            footer_view = View.inflate(this, R.layout.item_footer, null);
            footer_params_width = width;
            footer_params_height = heigth;

            layout_load_empty = footer_view.findViewById(R.id.layout_load_empty);
            layout_load_loading = footer_view.findViewById(R.id.layout_load_loading);
            layout_loadmore_end = footer_view.findViewById(R.id.layout_loadmore_end);
            layout_load_end = (TextView) footer_view.findViewById(R.id.layout_load_end);
            layout_load_error = (TextView) footer_view.findViewById(R.id.layout_load_error);

            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(width, heigth);
            footer_view.setLayoutParams(params);
            rcl_seller.addFooterView(footer_view);

            layout_load_empty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sellerPresenter.reqData(SELLER_LIST, Constants.LOAD_AUTO);
                }
            });
            
            layout_load_end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	if (datas.isEmpty()) {
                		sellerPresenter.reqData(SELLER_LIST, Constants.LOAD_AUTO);
					}else if (!isLastPage) {
						sellerPresenter.reqData(SELLER_LIST, Constants.LOAD_MORE);
					}
                }
            });


        }

        if (width != footer_params_width || heigth != footer_params_height) {

            footer_params_width = width;
            footer_params_height = heigth;

            ViewGroup.LayoutParams params = footer_view.getLayoutParams();
            params.width = width;
            params.height = heigth;

        }

        switch (type) {
            case LOAD_EMPTY:
                setViewVisible(layout_load_empty, V);
                setViewVisible(layout_load_loading, G);
                setViewVisible(layout_loadmore_end, G);
                setViewVisible(layout_load_end, G);
                setViewVisible(layout_load_error, G);
                break;
            case LOAD_MORE:

                setViewVisible(layout_load_empty, G);
                setViewVisible(layout_load_loading, G);
                setViewVisible(layout_loadmore_end, V);
                setViewVisible(layout_load_end, G);
                setViewVisible(layout_load_error, G);

                break;
            case LOAD_END:
                setViewVisible(layout_load_empty, G);
                setViewVisible(layout_load_loading, G);
                setViewVisible(layout_loadmore_end, G);
                setViewVisible(layout_load_end, V);
                setViewVisible(layout_load_error, G);
                setText(layout_load_end, end_error_msg);
                break;
            case LOAD_ERROR:
                setViewVisible(layout_load_empty, G);
                setViewVisible(layout_load_loading, G);
                setViewVisible(layout_loadmore_end, G);
                setViewVisible(layout_load_end, G);
                setViewVisible(layout_load_error, V);
                setText(layout_load_error, end_error_msg);
                break;
        }
    }

@Override
public void onRefresh() {
	sellerPresenter.reqData(SELLER_LIST, Constants.LOAD_TOP);
}


"""