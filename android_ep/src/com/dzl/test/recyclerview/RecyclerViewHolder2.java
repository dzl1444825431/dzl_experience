//package com.dzl.test.recyclerview;
//public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
//	private List<String> datas; 
//	private static final int IS_HEADER = 2; 
//	privatestaticfinalint IS_FOOTER = 3; 
//	privatestaticfinalint IS_NORMAL = 1; 
//
//
//publicRecyclerViewAdapter(List<String> datas) { this.datas = datas;
//    } @Overridepublic 
//    
//    RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        RecyclerViewHolder holder; //对不同的flag创建不同的Holder
//        if (viewType == IS_HEADER) {
//            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_header, viewGroup, false);
//            holder = new RecyclerViewHolder(view,IS_HEADER); return holder;
//        } elseif (viewType == IS_FOOTER) {
//            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_footer, viewGroup, false);
//            holder = new RecyclerViewHolder(view,IS_FOOTER); return holder;
//        }elseif(viewType==IS_NORMAL){
//            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false);
//            holder = new RecyclerViewHolder(view,IS_NORMAL); return holder;
//        } 
//        return null;
//    } 
//    @Override public void onBindViewHolder(final RecyclerViewHolder recyclerViewHolder, int position) { 
//    	//对不同的Item相应不同的操作
//    	if(position!=0&&position!=datas.size()+1&&recyclerViewHolder.viewType==IS_NORMAL){
//            recyclerViewHolder.mTextView.setText(datas.get(position - 1));
//        } if(position==0&&recyclerViewHolder.viewType==IS_HEADER){ //header recyclerViewHolder.mButton.setOnClickListener(new View.OnClickListener() { int i=0; @OverridepublicvoidonClick(View v) {
//                    recyclerViewHolder.mButton.setText(++i+"");
//                }
//            });
//        } if(position==datas.size()+1&&recyclerViewHolder.viewType==IS_FOOTER){ //footer }
//
//    } @Override
//    public int getItemCount() { return datas.size() + 2;
//
//    } @Override public int getItemViewType(int position) { 
//    	if (position == 0) { 
//    		return IS_HEADER;
//        } elseif(position == datas.size()+1){ 
//        	return IS_FOOTER;
//        }else { 
//        	return IS_NORMAL;
//        }
//    }
//
//    class RecyclerViewHolder extends RecyclerView.ViewHolder { 
//    	public TextView mTextView; public Button mButton; 
//    	publicint viewType; publicRecyclerViewHolder(View itemView,int viewType) { 
//    		super(itemView); 
//    	this.viewType = viewType; 
//    		if(viewType==IS_HEADER){
//                mButton = (Button) itemView.findViewById(R.id.button);
//            } if(viewType==IS_FOOTER){ //do some sthing } if(viewType==IS_NORMAL){
//                mTextView = (TextView) itemView.findViewById(R.id.tv_content);
//            }
//        }
//    }
//}