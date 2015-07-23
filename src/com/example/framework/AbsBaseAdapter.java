package com.example.framework;

import java.util.List;

import android.widget.BaseAdapter;
/**
 * 主要定义了一些操作集合的方法和实现了一部分BaseAdapter的抽象方法
 * 
 * @author anming.xam
 *
 * @param <T>
 */
public abstract class AbsBaseAdapter<T> extends BaseAdapter{
	protected List<T> data;
	
	public AbsBaseAdapter(List<T> data){
		this.data = data;
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public T getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public void clear(){
		data.clear();
		notifyDataSetChanged();
	}
	
	
	public void addAll(List<T> list){
		data.addAll(list);
		notifyDataSetChanged();
	}
	

    public void remove(T element) {
        data.remove(element);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        data.remove(index);
        notifyDataSetChanged();
    }

    /**
     * 用新的elements替换原来的data
     * @param element
     */
	public void replaceAll(List<T> elements) {
		this.data = elements;
		notifyDataSetChanged();
	}

}
