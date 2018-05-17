package com.lzp.router.compiler;

public class RouterMeta {
    public static enum RouterType {ACTIVITY, SERVICE, FRAGMENT, FRAGMENT_V4}

    private String group;
    private String path;
    /**
     */
    private String destination;
    private RouterType type;

    private RouterMeta() {
    }

    private RouterMeta(Builder builder) {
        path = builder.path;
        destination = builder.destination;
        type = builder.type;
        group = builder.group;
    }

    /**
     * [group,path,destination,type]
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[")
                .append("group:").append(group).append(",")
                .append("path:").append(path).append(",")
                .append("destination:").append(destination).append(",")
                .append("type:").append(type.name())
                .append("]");
        return sb.toString();
    }

    public static class Builder {
        private String path;
        private String destination;
        private RouterType type;
        private String group;

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder destination(String destination) {
            this.destination = destination;
            return this;
        }

        public Builder type(RouterType type) {
            this.type = type;
            return this;
        }

        public Builder group(String group) {
            this.group = group;
            return this;
        }

        public RouterMeta build() {
            return new RouterMeta(this);
        }

    }
}
