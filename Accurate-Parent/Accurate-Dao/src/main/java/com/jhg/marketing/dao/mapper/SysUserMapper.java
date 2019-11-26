package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.Permission;
import com.jhg.marketing.dao.domin.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jhg.marketing.dao.domin.SysUser;

import java.util.List;

@Mapper
public interface SysUserMapper extends tk.mybatis.mapper.common.Mapper<SysUser> {

    @Select({
            "SELECT su.*,wu.id weChatUserId,wu.id_card idCard,wu.main_flag mainFlag,wu.hospitalization_number hospitalizationNumber,wu.true_name trueName,wu.telephone " +
                    "FROM sys_user su,wechat_user wu " +
                    "WHERE (username=#{aName} " +
                    "OR open_id = #{aName}) " +
                    "AND su.id = wu.user_id " +
                    "AND wu.main_flag = 1"
    })
    SysUser findByUserName(String aName);

    @Select({
            "<script>" +
                    "SELECT su.*,wu.id weChatUserId,wu.id_card idCard,wu.main_flag," +
                    "wu.hospitalization_number hospitalizationNumber," +
                    "wu.true_name trueName,wu.telephone,stuff((" +
                    "SELECT ',' + r.name " +
                    "FROM user_role ur,sys_role r " +
                    "where ur.role_id = r.id " +
                    "and ur.user_id = su.id " +
                    "for xml path('')" +
                    "),1,1,'') sysRoleStr,stuff((" +
                    "SELECT ',' + dt.name " +
                    "FROM wechat_user_diseases_type wudt,diseases_type dt " +
                    "where wudt.diseases_type_id = dt.id " +
                    "AND wudt.wechat_user_id = wu.id " +
                    "for xml path('')" +
                    "),1,1,'') diseasesTypeStr " +
                    "FROM sys_user su,wechat_user wu " +
                    "WHERE su.id = wu.user_id " +
                    "AND wu.main_flag = 1 " +
                    "AND type = #{type}" +
                    "<if test=\"username != null and username != ''\">" +
                    " AND (su.username LIKE '%' + #{username} + '%'" +
                    " OR (wu.main_flag = 1 " +
                    "AND wu.true_name LIKE '%' + #{username} + '%'))" +
                    "</if>" +
                    "</script>"
    })
    List<SysUser> listUser(SysUser user);

    @Delete({
            "<script>" +
                    "DELETE FROM sys_user " +
                    "WHERE id IN " +
                    "<foreach collection=\"sysUserList\" open=\"(\" close=\")\" separator=\",\" item=\"item\">" +
                    "#{item}" +
                    "</foreach>" +
                    "</script>"
    })
    Integer deleteBySysUserIdList(@Param("sysUserList") List<String> sysUserList);

    @Select({
            "SELECT su.*,wu.id_card,wu.main_flag,wu.hospitalization_number hospitalizationNumber,wu.true_name trueName,wu.telephone " +
                    "FROM sys_user su,wechat_user wu " +
                    "WHERE su.id = wu.user_id " +
                    "AND su.open_id = #{openId} " +
                    "AND wu.main_flag = 1"
    })
    SysUser listUserByOpenId(String openId);

    @Select({
            "SELECT ur.role_id " +
                    "FROM sys_user su,user_role ur " +
                    "WHERE su.id = ur.user_id " +
                    "AND su.id = #{id}"
    })
    List<String> listRoleIdByUserId(Integer id);


    @Select({
            "SELECT su.*,wu.id weChatUserId,wu.id_card idCard,wu.main_flag mainFlag,wu.hospitalization_number hospitalizationNumber,wu.true_name trueName,wu.telephone " +
                    "FROM sys_user su,wechat_user wu " +
                    "WHERE su.id = wu.user_id " +
                    "AND su.id = #{userId} " +
                    "AND wu.main_flag = 1"
    })
    SysUser getUserInfo(Integer userId);

    @Select({
            "SELECT su.id,su.type,su.gender,su.username,wu.true_name trueName,wu.telephone," +
                    "su.email,su.enabled,wu.id weChatUserId " +
                    "FROM sys_user su,wechat_user wu " +
                    "WHERE su.id = wu.user_id " +
                    "AND su.id = #{id} " +
                    "AND wu.main_flag = 1"
    })
    SysUser getSysUserInfo(Integer id);

    @Select({
            "SELECT su.id,wu.id weChatUserId,wu.true_name trueName,su.head_img_url headImgUrl,wu.description " +
                    "FROM sys_user su,wechat_user wu " +
                    "WHERE wu.position = 1 " +
                    "AND wu.show_index = 1 " +
                    "AND su.id = wu.user_id " +
                    "AND wu.main_flag = 1"
    })
    List<SysUser> getSomeExpertInfo();

    @Select({
            "SELECT wu.true_name " +
                    "FROM sys_user su,wechat_user wu " +
                    "WHERE su.id = wu.user_id " +
                    "AND wu.main_flag = 1 " +
                    "AND su.open_id = #{openId} "
    })
    String getMainTrueName(String openId);

    @Select({
            "SELECT wu.true_name trueName,wu.id_card idCard,wu.main_flag mainFlag,wu.enable " +
                    "FROM sys_user su,wechat_user wu " +
                    "WHERE su.id = wu.user_id " +
                    "AND su.open_id = #{openId} " +
                    "AND su.type = 2 "
    })
    List<SysUser> listHospitalCard(String openId);

    @Select({
            "<script>" +
                    "SELECT DISTINCT su.id, su.open_id openId, wu.true_name trueName, wu.id_card idCard " +
                    "FROM sys_user su,wechat_user wu " +
                    "LEFT JOIN wechat_user_diseases_type wudt " +
                    "ON wudt.wechat_user_id = wu.id " +
                    "WHERE su.id = wu.user_id " +
//                    "AND wudt.wechat_user_id = wu.id " +
//                    "AND wu.main_flag = 1 " +
                    "AND su.type = 2 " +
                    "<if test=\"diseasesTypeId != null and diseasesTypeId != ''\">" +
                    "AND wudt.diseases_type_id = #{diseasesTypeId} " +
                    "</if>" +
                    "</script>"
    })
    List<SysUser> listUserByDiseasesType(@Param("diseasesTypeId") Integer diseasesTypeId);

    @Select({
            "SELECT wu.id " +
                    "FROM sys_user su, wechat_user wu " +
                    "WHERE su.id = wu.user_id " +
                    "AND su.open_id = #{openId} " +
                    "AND wu.main_flag = 1"
    })
    Integer getWeChatUserId(String openId);

    @Select({
            "SELECT p.*" +
                    "FROM user_role ur,role_permission rp,permission p " +
                    "WHERE ur.user_id = #{userId} " +
                    "AND ur.role_id = rp.role_id " +
                    "AND rp.permission_id = p.id"
    })
    List<Permission> getBIPermission(Integer userId);
}
