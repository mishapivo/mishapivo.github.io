package com.audacityit.audacity.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.audacityit.audacity.R;
import com.audacityit.audacity.api.response_layer.client.Tag;
import com.audacityit.audacity.util.CustomDialogListAdapter;
import com.audacityit.audacity.util.TagGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.audacityit.audacity.util.UtilMethods.loadJSONFromAsset;

/**
 * Created by Tushar Saha on 1/11/16.
 * Audacity IT Solutions Ltd.
 */
public class GetStartedActivity extends AppCompatActivity {

    private TextView categoryTv;
    private CheckedTextView checkAllTv;
    private LayoutInflater inflater = null;
    private CustomDialogListAdapter dialogListAdapter;
    private String[] projectOptions = null;
    private TagGroup tagOptions;
    private List<String> checkedItems = null;
    private AlertDialog dialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        categoryTv = (TextView) findViewById(R.id.categoryTv);
        tagOptions = (TagGroup) findViewById(R.id.tagOptions);
        tagOptions.setOnTagClickListener(mTagClickListener);
        if(checkedItems==null) {
            categoryTv.setVisibility(View.VISIBLE);
        } else {
            categoryTv.setVisibility(View.GONE);
        }
        categoryTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inflater = LayoutInflater.from(GetStartedActivity.this);
                View customView = inflater.inflate(R.layout.layout_dialog_list, null, false);
                ListView dialogListView = (ListView) customView.findViewById(R.id.dialogListView);
                checkAllTv = (CheckedTextView) customView.findViewById(R.id.checkAllTv);
                checkAllTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkAllTv.isChecked()) {
                            checkAllTv.setChecked(false);
                            if(dialogListAdapter!=null) {
                                dialogListAdapter.unCheckAll();
                            }
                        } else {
                            checkAllTv.setChecked(true);
                            if(dialogListAdapter!=null) {
                                dialogListAdapter.checkAll();
                            }
                        }

                    }
                });
                dialogListAdapter = new CustomDialogListAdapter(GetStartedActivity.this, R.layout.layout_dialog_list_row,
                        android.R.id.text1, getProjectOptions());
                dialogListView.setAdapter(dialogListAdapter);
                dialogListView.setOnItemClickListener(myOnItemClickListener);

                dialog = new AlertDialog.Builder(GetStartedActivity.this)
                        .setCancelable(true)
                        .setView(customView)
                        .setNegativeButton(getResources().getString(android.R.string.cancel), null)
                        .setPositiveButton(getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                checkedItems = dialogListAdapter.getCheckedItems();
                                if(checkedItems!=null && checkedItems.size()>0) {
                                    categoryTv.setVisibility(View.GONE);
                                    checkedItems.add("+");
                                    tagOptions.setTags(checkedItems.toArray(new String[checkedItems.size()]));
                                } else {
                                    tagOptions.setTag(new String[]{});
                                    categoryTv.setVisibility(View.VISIBLE);

                                }
                            }
                        }).create();

                dialog.show();
            }
        });
    }

    private String[] getProjectOptions() {
        String jsonString = loadJSONFromAsset(GetStartedActivity.this, "filter");
        if (!TextUtils.isEmpty(jsonString)) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString).optJSONObject("filter");
                if (jsonObject != null) {
                    JSONArray jsonArray = jsonObject.optJSONArray("options");
                    if (jsonArray != null) {
                        projectOptions = new String[jsonArray.length()];
                        for (int i = 0; i < jsonArray.length(); i++) {
                            projectOptions[i] = jsonArray.getJSONObject(i).optString("title");
                        }
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return projectOptions;
    }

    AdapterView.OnItemClickListener myOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            dialogListAdapter.toggleChecked(position);

        }
    };

    private TagGroup.OnTagClickListener mTagClickListener = new TagGroup.OnTagClickListener() {
        @Override
        public void onTagClick(String text) {
            if(text.equalsIgnoreCase("+")) {
                if(dialog!=null) {
                    dialog.show();
                }
            }
        }

        @Override
        public void onTagClick(Tag tag) {

        }
    };

}
