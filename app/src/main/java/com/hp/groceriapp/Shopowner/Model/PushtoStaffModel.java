package com.hp.groceriapp.Shopowner.Model;

import java.util.List;

public class PushtoStaffModel {
    /**
     * multicast_id : 412296907495069875
     * success : 0
     * failure : 1
     * canonical_ids : 0
     * results : [{"error":"InvalidRegistration"}]
     */

    private long multicast_id;
    private int success;
    private int failure;
    private int canonical_ids;
    private List<ResultsBean> results;

    public long getMulticast_id() {
        return multicast_id;
    }

    public void setMulticast_id(long multicast_id) {
        this.multicast_id = multicast_id;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public int getCanonical_ids() {
        return canonical_ids;
    }

    public void setCanonical_ids(int canonical_ids) {
        this.canonical_ids = canonical_ids;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * error : InvalidRegistration
         */

        private String error;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
