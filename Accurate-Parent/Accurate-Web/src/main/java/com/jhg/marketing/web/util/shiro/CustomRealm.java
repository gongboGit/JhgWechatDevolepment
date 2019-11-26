package com.jhg.marketing.web.util.shiro;

import com.jhg.marketing.dao.domin.SysUser;
import com.jhg.marketing.dao.mapper.RolePermissionMapper;
import com.jhg.marketing.dao.mapper.SysUserMapper;
import com.jhg.marketing.web.config.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class CustomRealm extends AuthorizingRealm {

//     /**
//      * 告诉shiro如何根据获取到的用户信息中的密码和盐值来校验密码
//      */
//     public CustomRealm() {
//         // 设置用于匹配密码的CredentialsMatcher
//         HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
//         hashMatcher.setHashAlgorithmName(ShiroUtil.HASH_ALGORITHM_NAME);
//         hashMatcher.setHashIterations(ShiroUtil.HASH_ITERATIONS);
//         this.setCredentialsMatcher(hashMatcher);
//     }

    /**
     * 定义如何获取用户信息的业务逻辑，给shiro做登录
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("--开始认证");
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm");
        }
        SysUserMapper sysUserMapper = SpringContextUtil.getApplicationContext().getBean(SysUserMapper.class);
        SysUser user = sysUserMapper.findByUserName(username);

        if (user == null) {
            throw new UnknownAccountException("No account found for admin [" + username + "]");
        }

        if (!user.getEnabled()) {
            throw new DisabledAccountException("The account is be disabled");
        }

//         ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());
//         return new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, getName());
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }


    /**
     * 定义如何获取用户的角色和权限的逻辑，给shiro做权限判断
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("--开始授权");
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        SysUser user = (SysUser) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //设置角色
        SysUserMapper sysUserMapper = SpringContextUtil.getApplicationContext().getBean(SysUserMapper.class);
        List<String> roleList = sysUserMapper.listRoleIdByUserId(user.getId());
        Set<String> roleSet = new HashSet<>(roleList);
        info.setRoles(roleSet);
        //设置权限
        RolePermissionMapper rolePermissionMapper = SpringContextUtil.getApplicationContext().getBean(RolePermissionMapper.class);
        Set<String> permissionSet = new HashSet<>(rolePermissionMapper.listPermissionByRoleIdList(roleList));
        info.setStringPermissions(permissionSet);
        return info;
    }

    /**
     * 重写方法,清除当前用户的的 授权缓存
     *
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的 认证缓存
     *
     * @param principals
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 自定义方法：清除所有 授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 自定义方法：清除所有 认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 自定义方法：清除所有的认证缓存和授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}