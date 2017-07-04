package com.syahiramir.bmwchallenge;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import com.syahiramir.bmwchallenge.activity.MapActivity;
import com.syahiramir.bmwchallenge.model.Location;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Syahir on 7/4/17.
 * test for mapActivity
 */

@SmallTest
@RunWith(AndroidJUnit4.class)
public class MapActivityTest {

    @Rule
    public final ActivityTestRule<MapActivity> rule  = new  ActivityTestRule<MapActivity>(MapActivity.class)
    {
        @Override
        protected Intent getActivityIntent() {
            InstrumentationRegistry.getTargetContext();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.putExtra("location", new Location(35862, "Doughnut Vault Canal",41.883976,-87.639346,"11 N Canal St L30 Chicago, IL 60606","2017-07-04T19:50:52.293"));
            return intent;
        }
    };

    @Test
    public void ensureIntentDataIsDisplayed() throws Exception {
        MapActivity activity = rule.getActivity();

        View nameTxt = activity.findViewById(R.id.nameTxt);
        View addressTxt = activity.findViewById(R.id.addressTxt);

        assertThat(nameTxt,notNullValue());
        assertThat(nameTxt, instanceOf(TextView.class));
        assertThat(addressTxt,notNullValue());
        assertThat(addressTxt, instanceOf(TextView.class));
        TextView nameTxtTv = (TextView) nameTxt;
        TextView addressTxtTv = (TextView) addressTxt;
        assertThat(nameTxtTv.getText().toString(),is("Doughnut Vault Canal"));
        assertThat(addressTxtTv.getText().toString(),is("11 N Canal St L30 Chicago, IL 60606"));
    }
}