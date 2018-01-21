package com.pascal.pray.android.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Pascal on 2018/1/7.
 */

public class JsonData {
    /**
     * statusCode : 200
     * statusMessage : OK
     * data : [{"id":10,"type":"dev_mydnavn_dialog_update_app","data":{"FU":false,"messages":{"en":{"body":"For improving user experience, we made some changes which needs you update your app. The minimum working version is v0.9 and now you are using {current_version_text}. Please kindly update your app before using. Sorry for any inconvience.","title":"Update version"},"vn":{"body":"QOO"},"zh-Hans":{"body":"您目前使用的版本为{current_version_text},请更新版本至v0.9","title":"更新版本"},"zh-Hant":{"body":"您目前使用的版本為{current_version_text},請更新版本至v0.9","title":"更新版本"}},"aos_mini_version":"1.3","ios_mini_version":"1.3"},"created_at":"2017-07-28 14:57:35.148773","updated_at":"2017-07-28 14:57:35.148773"}]
     */

    private int statusCode;
    private String statusMessage;
    private List<Data> data;

    public JsonData(int statusCode ,String statusMessage ,  List<Data> data){
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        /**
         * id : 10
         * type : dev_mydnavn_dialog_update_app
         * data : {"FU":false,"messages":{"en":{"body":"For improving user experience, we made some changes which needs you update your app. The minimum working version is v0.9 and now you are using {current_version_text}. Please kindly update your app before using. Sorry for any inconvience.","title":"Update version"},"vn":{"body":"QOO"},"zh-Hans":{"body":"您目前使用的版本为{current_version_text},请更新版本至v0.9","title":"更新版本"},"zh-Hant":{"body":"您目前使用的版本為{current_version_text},請更新版本至v0.9","title":"更新版本"}},"aos_mini_version":"1.3","ios_mini_version":"1.3"}
         * created_at : 2017-07-28 14:57:35.148773
         * updated_at : 2017-07-28 14:57:35.148773
         */

        private int id;
        private String type;
        private DataBean data;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public static class DataBean {
            /**
             * FU : false
             * messages : {"en":{"body":"For improving user experience, we made some changes which needs you update your app. The minimum working version is v0.9 and now you are using {current_version_text}. Please kindly update your app before using. Sorry for any inconvience.","title":"Update version"},"vn":{"body":"QOO"},"zh-Hans":{"body":"您目前使用的版本为{current_version_text},请更新版本至v0.9","title":"更新版本"},"zh-Hant":{"body":"您目前使用的版本為{current_version_text},請更新版本至v0.9","title":"更新版本"}}
             * aos_mini_version : 1.3
             * ios_mini_version : 1.3
             */

            private boolean FU;
            private MessagesBean messages;
            private String aos_mini_version;
            private String ios_mini_version;

            public boolean isFU() {
                return FU;
            }

            public void setFU(boolean FU) {
                this.FU = FU;
            }

            public MessagesBean getMessages() {
                return messages;
            }

            public void setMessages(MessagesBean messages) {
                this.messages = messages;
            }

            public String getAos_mini_version() {
                return aos_mini_version;
            }

            public void setAos_mini_version(String aos_mini_version) {
                this.aos_mini_version = aos_mini_version;
            }

            public String getIos_mini_version() {
                return ios_mini_version;
            }

            public void setIos_mini_version(String ios_mini_version) {
                this.ios_mini_version = ios_mini_version;
            }

            public static class MessagesBean {
                /**
                 * en : {"body":"For improving user experience, we made some changes which needs you update your app. The minimum working version is v0.9 and now you are using {current_version_text}. Please kindly update your app before using. Sorry for any inconvience.","title":"Update version"}
                 * vn : {"body":"QOO"}
                 * zh-Hans : {"body":"您目前使用的版本为{current_version_text},请更新版本至v0.9","title":"更新版本"}
                 * zh-Hant : {"body":"您目前使用的版本為{current_version_text},請更新版本至v0.9","title":"更新版本"}
                 */

                private EnBean en;
                private VnBean vn;
                @SerializedName("zh-Hans")
                private ZhHansBean zhHans;
                @SerializedName("zh-Hant")
                private ZhHantBean zhHant;

                public EnBean getEn() {
                    return en;
                }

                public void setEn(EnBean en) {
                    this.en = en;
                }

                public VnBean getVn() {
                    return vn;
                }

                public void setVn(VnBean vn) {
                    this.vn = vn;
                }

                public ZhHansBean getZhHans() {
                    return zhHans;
                }

                public void setZhHans(ZhHansBean zhHans) {
                    this.zhHans = zhHans;
                }

                public ZhHantBean getZhHant() {
                    return zhHant;
                }

                public void setZhHant(ZhHantBean zhHant) {
                    this.zhHant = zhHant;
                }

                public static class EnBean {
                    /**
                     * body : For improving user experience, we made some changes which needs you update your app. The minimum working version is v0.9 and now you are using {current_version_text}. Please kindly update your app before using. Sorry for any inconvience.
                     * title : Update version
                     */

                    private String body;
                    private String title;

                    public String getBody() {
                        return body;
                    }

                    public void setBody(String body) {
                        this.body = body;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }
                }

                public static class VnBean {
                    /**
                     * body : QOO
                     */

                    private String body;

                    public String getBody() {
                        return body;
                    }

                    public void setBody(String body) {
                        this.body = body;
                    }
                }

                public static class ZhHansBean {
                    /**
                     * body : 您目前使用的版本为{current_version_text},请更新版本至v0.9
                     * title : 更新版本
                     */

                    private String body;
                    private String title;

                    public String getBody() {
                        return body;
                    }

                    public void setBody(String body) {
                        this.body = body;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }
                }

                public static class ZhHantBean {
                    /**
                     * body : 您目前使用的版本為{current_version_text},請更新版本至v0.9
                     * title : 更新版本
                     */

                    private String body;
                    private String title;

                    public String getBody() {
                        return body;
                    }

                    public void setBody(String body) {
                        this.body = body;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }
                }
            }
        }
    }
}
