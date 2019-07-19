package com.github.groupiedemo;

/*@GlideModule
public class MyAppGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        // 缓存大小100 MB
        int diskCacheSizeBytes = 1024 * 1024 * 100;
        // 优先外部存储，没有则自动选择内部存储
        builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context, "glide_image_cache", diskCacheSizeBytes));
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        // registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }

    @Override
    public boolean isManifestParsingEnabled() {
        // 如果你已经迁移到 Glide v4 的 AppGlideModule 和 LibraryGlideModule ，你可以完全禁用清单解析。
        // 这样可以改善 Glide 的初始启动时间，并避免尝试解析元数据时的一些潜在问题。
        // false，禁用清单解析
        return false;
    }
}*/
