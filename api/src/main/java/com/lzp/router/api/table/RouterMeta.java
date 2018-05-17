package com.lzp.router.api.table;

/**
 * copy from com.lzp.router.compiler.RouterMeta
 * 用来表示一个路由节点
 */
public class RouterMeta {
    public static enum RouterType {ACTIVITY, SERVICE, FRAGMENT, FRAGMENT_V4}

    private String path;
    /**
     * 目标对象的全限定名
     */
    private Class<?> destination;
    private RouterType type;
    private String group;

    private RouterMeta() {
    }

    private RouterMeta(Builder builder) {
        path = builder.path;
        destination = builder.destination;
        type = builder.type;
        group = builder.group;
    }

    public String getPath() {
        return path;
    }

    public Class<?> getDestination() {
        return destination;
    }

    public RouterType getType() {
        return type;
    }

    public String getGroup() {
        return group;
    }

    /**
     * [path,destination,type]
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[")
                .append(group).append(",")
                .append(path).append(",")
                .append(destination).append(",")
                .append(type.name())
                .append("]");
        return sb.toString();
    }

    public static class Builder {
        private String path;
        private Class<?> destination;
        private RouterType type;
        private String group;

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder destination(Class<?> destination) {
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

        public Builder setValue(String key, String value) throws ClassNotFoundException, NoSuchMethodException {
            if (key.equals("path")) {
                return path(value);
            } else if (key.equals("destination")) {
                return destination(Class.forName(value));
            } else if (key.equals("type")) {
                return type(RouterType.valueOf(value));
            } else if (key.equals("group")) {
                return group(value);
            }
            throw new NoSuchMethodException("method name " + key);
        }

        public RouterMeta build() {
            return new RouterMeta(this);
        }

    }
}
