package com.zhangf.unnamed.http.api;

import com.zhangf.unnamed.module.main.model.HisThreadResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 75232 on 2018/8/23
 * Email：752323877@qq.com
 */
public interface HisThreadApi {
    @GET("iyz_index.php")
    Observable<HisThreadResult> fetchHisThread(@Query("iyzmobile") String iyzmobile,
                                               @Query("module")String module,
                                               @Query("version")String version,
                                               @Query("type")String type,
                                               @Query("uid")String uid);
}
