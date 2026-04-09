package com.chad.baserecyclerviewadapterhelper.activity.itemclick.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.baserecyclerviewadapterhelper.databinding.ItemClickChildviewBinding;
import com.chad.baserecyclerviewadapterhelper.databinding.ItemClickViewBinding;
import com.chad.baserecyclerviewadapterhelper.databinding.ItemLongClickChildviewBinding;
import com.chad.baserecyclerviewadapterhelper.databinding.ItemLongClickViewBinding;
import com.chad.baserecyclerviewadapterhelper.entity.ClickEntity;
import com.chad.library.adapter4.BaseMultiItemAdapter;
import com.mozhimen.xmlk.adapter4.ext.bases.BaseViewHolder;

import java.util.List;

/**
 *
 */
public class ItemClickAdapter extends BaseMultiItemAdapter<ClickEntity, BaseViewHolder> {

    @NonNull
    @Override
    public String getNAME() {
        return "ItemClickAdapter";
    }

    @NonNull
    @Override
    public String getTAG() {
        return "ItemClickAdapter>>>>>";
    }

    static class ItemViewVH extends BaseViewHolder {

        ItemClickViewBinding viewBinding;

        public ItemViewVH(@NonNull ItemClickViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }

        public ItemViewVH(@NonNull ViewGroup parent) {
            this(ItemClickViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
    }

    static class ItemChildVH extends BaseViewHolder {

        ItemClickChildviewBinding viewBinding;

        public ItemChildVH(@NonNull ItemClickChildviewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }

        public ItemChildVH(@NonNull ViewGroup parent) {
            this(ItemClickChildviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
    }

    static class ItemLongClickVH extends BaseViewHolder {

        ItemLongClickViewBinding viewBinding;

        public ItemLongClickVH(@NonNull ItemLongClickViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }

        public ItemLongClickVH(@NonNull ViewGroup parent) {
            this(ItemLongClickViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
    }

    static class ItemChildLongClickVH extends BaseViewHolder {

        ItemLongClickChildviewBinding viewBinding;

        public ItemChildLongClickVH(@NonNull ItemLongClickChildviewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }

        public ItemChildLongClickVH(@NonNull ViewGroup parent) {
            this(ItemLongClickChildviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
    }

    /**
     * 构造方法
     */
    public ItemClickAdapter(List<ClickEntity> data) {
        super(data);

        addItemType(new OnMultiItemAdapterListener<ClickEntity, BaseViewHolder>() {
            @Override
            public void onBindViewHolder(BaseViewHolder baseViewHolder, @Nullable ClickEntity clickEntity, int i, @NonNull List<?> list) {

            }

            @Override
            public int getItemViewType() {
                return ClickEntity.CLICK_ITEM_VIEW;
            }

            @NonNull
            @Override
            public BaseViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup parent, int viewType) {
                return new ItemViewVH(parent);
            }

            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, ClickEntity item, int position) {
            }

            @Override
            public void onViewDetachedFromWindow(BaseViewHolder holder, @Nullable ClickEntity item, @Nullable Integer position) {
                if (holder instanceof ItemViewVH) {
                    System.out.println("---------------------- >> onViewDetachedFromWindow ItemViewVH");
                }
            }
        }).addItemType( new OnMultiItemAdapterListener<ClickEntity, BaseViewHolder>() {

            @Override
            public void onBindViewHolder(BaseViewHolder baseViewHolder, @Nullable ClickEntity clickEntity, int i, @NonNull List<?> list) {

            }

            @Override
            public int getItemViewType() {
                return ClickEntity.CLICK_ITEM_CHILD_VIEW;
            }

            @NonNull
            @Override
            public BaseViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup parent, int viewType) {
                return new ItemChildVH(parent);
            }

            @Override
            public void onBindViewHolder(BaseViewHolder holder, @Nullable ClickEntity item, int position) {

            }
        }).addItemType( new OnMultiItemAdapterListener<ClickEntity, BaseViewHolder>() {
            @Override
            public void onBindViewHolder(BaseViewHolder baseViewHolder, @Nullable ClickEntity clickEntity, int i, @NonNull List<?> list) {

            }

            @Override
            public int getItemViewType() {
                return ClickEntity.LONG_CLICK_ITEM_VIEW;
            }

            @NonNull
            @Override
            public BaseViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup parent, int viewType) {
                return new ItemLongClickVH(parent);
            }

            @Override
            public void onBindViewHolder(BaseViewHolder holder, @Nullable ClickEntity item, int position) {
            }

        }).addItemType(new OnMultiItemAdapterListener<ClickEntity, BaseViewHolder>() {

            @Override
            public void onBindViewHolder(BaseViewHolder baseViewHolder, @Nullable ClickEntity clickEntity, int i, @NonNull List<?> list) {

            }

            @Override
            public int getItemViewType() {
                return ClickEntity.LONG_CLICK_ITEM_CHILD_VIEW;
            }

            @NonNull
            @Override
            public BaseViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup parent, int viewType) {
                return new ItemChildLongClickVH(parent);
            }

            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, ClickEntity item, int position) {
            }
        }).onItemViewType(new OnItemViewTypeListener<ClickEntity>() {
            @Override
            public int onItemViewType(int position, @NonNull List<? extends ClickEntity> list) {
                return list.get(position).getItemType();
            }
        });

    }


}
