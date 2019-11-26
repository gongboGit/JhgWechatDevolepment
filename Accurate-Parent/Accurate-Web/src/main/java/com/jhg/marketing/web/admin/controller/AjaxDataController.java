package com.jhg.marketing.web.admin.controller;

import com.alibaba.fastjson.JSON;
import com.jhg.marketing.dao.domin.*;
import com.jhg.marketing.dao.domin.material.MessageNews;
import com.jhg.marketing.dao.mapper.DiseasesTypeMapper;
import com.jhg.marketing.dao.mapper.PermissionMapper;
import com.jhg.marketing.dao.mapper.SysRoleMapper;
import com.jhg.marketing.dao.mapper.SysUserMapper;
import com.jhg.marketing.web.admin.service.Message;
import com.jhg.marketing.web.service.*;
import com.jhg.marketing.web.util.ApplicationUtil;
import com.jhg.marketing.web.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("admin/ajax")
public class AjaxDataController {

    @Value("${user.default.visitpath}")
    private String visitpath;

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private Role2PermissionService role2PermissionService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private MessageNewsService messageNewsService;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private FileUploadUtil fileUploadUtil;

    @PostMapping("login")
    public Message login(String username, String userpassword, boolean rememberMe) {
        log.info("--login --rememberMe:" + rememberMe);
        Message msg = fail();
        Subject subject = SecurityUtils.getSubject();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(userpassword)) {
            msg.setMsg("用户名或密码不能为空！");
            return msg;
        }
        try {
            subject.login(new UsernamePasswordToken(username, userpassword, rememberMe));
            log.info(rememberMe + "" + subject.isRemembered());
            SysUser user = (SysUser) subject.getPrincipal();
            user.setRememberMe(rememberMe);
            Session session = subject.getSession();
            session.setAttribute("user", user);
            SysUser userParam = new SysUser();
            userParam.setLastLoginTime(new Date());
            userParam.setId(user.getId());
            sysUserMapper.updateByPrimaryKeySelective(userParam);
        } catch (Exception e) {
            msg.setMsg(e.getMessage());
            return msg;
        }

        return success();
    }

    public static Message success() {
        return new Message() {
            {
                setCode(1);
                setMsg("操作成功");
            }
        };
    }

    public static Message fail() {
        return new Message() {
            {
                setCode(0);
                setMsg("操作失败");
            }
        };
    }

    /**
     * 查询所有用户
     *
     * @param sysUser
     * @return
     */
    @RequestMapping("listSysUser")
    public Message listSysUser(SysUser sysUser) {
        return Message.success("获取成功！", sysUserService.listUser(sysUser));
    }

    /**
     * 编辑用户
     *
     * @return
     */
    @RequestMapping("postsysuser")
    public boolean postSysUser(SysUser sysUser) {
        Integer userId = sysUser.getId();
        if (userId != null) {
            //修改
            sysUserService.updateSysUser(sysUser);
        } else {
            //新增
            sysUserService.insertSysUser(sysUser);
        }
        return true;
    }

    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     */
    @RequestMapping("userdel")
    public Message userDel(String ids) {
        return sysUserService.deleteSysUser(ids) ? success() : fail();
    }

    /**
     * 批量删除角色
     *
     * @param ids
     * @return
     */
    @RequestMapping("roledel")
    public Message roleDel(String ids) {
        return sysRoleService.deleteSysRole(ids) ? success() : fail();
    }

    /**
     * 查询所有权限
     *
     * @return
     */
    @RequestMapping("listPermission")
    public String listPermission(@RequestParam(value = "roleId", required = false) Integer roleId) {
        return JSON.toJSONString(permissionMapper.listPermissionByRoleId(roleId));
    }

    /**
     * 修改权限
     *
     * @param sysRole2MenuWithRoleId
     * @return
     */
    @RequestMapping("role2perms")
    public Message role2perms(@RequestBody SysRole2MenuWithRoleId sysRole2MenuWithRoleId) {
        return role2PermissionService.updateRole2Perms(sysRole2MenuWithRoleId) ? success() : fail();
    }

    /**
     * 批量删除菜单
     *
     * @param ids
     * @return
     */
    @RequestMapping("permdel")
    public Message permDel(String ids) {
        return permissionService.deletePermission(ids) ? success() : fail();
    }

    @Autowired
    private HospitalIntroductionService hospitalIntroductionService;

    /**
     * 查询所有医院介绍
     *
     * @return
     */
    @RequestMapping("listHospitalIntroduction")
    public List<HospitalIntroduction> listHospitalIntroduction() {
        return hospitalIntroductionService.listHospitalIntroduction();
    }

    /**
     * 删除医院介绍
     *
     * @param ids
     * @return
     */
    @RequestMapping("hospitalIntroductionDel")
    public Message hospitalIntroductionDel(String ids) {
        return hospitalIntroductionService.deleteHospitalIntroduction(ids) ? success() : fail();
    }

    /**
     * 查询医院介绍
     *
     * @return
     */
    @RequestMapping("listMessageNews")
    public List<MessageNews> listMessageNews() {
        return messageNewsService.listMessageNews();
    }

    /**
     * 编辑医院介绍
     *
     * @param messageNews
     * @param file
     * @return
     */
    @PostMapping("postIntroduction")
    public boolean postIntroduction(MessageNews messageNews, @RequestParam("file") MultipartFile file) {
        Integer id = messageNews.getId();
        if (id == null) {
            messageNewsService.insertMessageNews(messageNews, file);
        } else {
            messageNewsService.updateMessageNews(messageNews, file);
        }
        return true;
    }

    /**
     * 上传图片(富文本)
     *
     * @return
     */
    @RequestMapping("uploadFile")
    public Map uploadFile(MultipartFile file) throws Exception {
        String imageFileUrl = visitpath + fileUploadUtil.upload(file, "image");
        //构造返回参数
        Map<String, Object> map = new HashMap();
        Map<String, Object> mapData = new HashMap();
        map.put("code", "0");//0表示成功，1失败
        map.put("msg", "上传成功");//提示消息
        map.put("data", mapData);//提示消息
        mapData.put("src", imageFileUrl);//图片url
        mapData.put("title", "图片");//图片名称，这个会显示在输入框里
        return map;
    }

    /**
     * 更新医院介绍状态
     *
     * @param id
     * @return
     */
    @RequestMapping("updateHospitalIntroduction")
    public boolean updateHospitalIntroduction(Integer id, Boolean status) {
        return messageNewsService.updateHospitalIntroduction(id, status);
    }

    @Autowired
    private DiseasesTypeMapper diseasesTypeMapper;

    /**
     * 查询所有的病种类型
     *
     * @return
     */
    @RequestMapping("listDiseasesType")
    public Message listDiseasesType() {
        return Message.success("获取成功！", diseasesTypeMapper.selectAll());
    }

    /**
     * 新增病种类型
     *
     * @param name
     * @return
     */
    @RequestMapping("insertDiseasesType")
    public Message insertDiseasesType(String name) {
        return Message.success("新增成功！", diseasesTypeMapper.insert(new DiseasesType() {{
            setName(name);
        }}));
    }

    /**
     * 修改病种类型
     *
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("updateDiseasesType")
    public Message updateDiseasesType(Integer id, String name) {
        return Message.success("修改成功！", diseasesTypeMapper.updateByPrimaryKey(new DiseasesType() {{
            setId(id);
            setName(name);
        }}));
    }

    /**
     * 删除病种
     *
     * @param id
     * @return
     */
    @RequestMapping("deleteDiseasesType")
    public Message deleteDiseasesType(Integer id) {
        return Message.success("删除成功！", diseasesTypeMapper.deleteByPrimaryKey(id));
    }

    /**
     * 根据病种获取微信用户
     *
     * @param diseasesTypeId
     * @return
     */
    @RequestMapping("listUserByDiseasesType")
    public Message listUserByDiseasesType(Integer diseasesTypeId) {
        return Message.success("获取成功！", sysUserMapper.listUserByDiseasesType(diseasesTypeId));
    }

    /**
     * 获取未禁用角色列表
     *
     * @return
     */
    @RequestMapping("listRole")
    public Message listRole() {
        return Message.success("获取成功！", sysRoleService.listRoles());
    }

    //    @Autowired
//    private SysRoleMapper sysRoleMapper;
//    @RequestMapping("listRoleByFlag")
//    public Message listRoleByFlag(Integer flag) {
//        return Message.success("获取成功！",sysRoleMapper.select(new SysRole(){{
//            setEnabled(true);
//
//        }}) );
//    }
//    @Autowired
//    SysUserMapper sysUserMapper;

    /**
     * 修改密码
     *
     * @param password
     * @param newPassword
     * @return
     */
    @RequestMapping("updatePassword")
    public Message updatePassword(String password, String newPassword) {
        Integer userId = ApplicationUtil.getSessionUserId();
        List<SysUser> userList = sysUserMapper.select(new SysUser() {{
            setId(userId);
            setPassword(password);
        }});
        if (userList != null && userList.size() != 0) {
            return Message.success("修改成功！", sysUserMapper.updateByPrimaryKeySelective(new SysUser() {{
                setId(userId);
                setPassword(newPassword);

            }}));
        }
        return Message.fail("密码不正确");
    }
}
