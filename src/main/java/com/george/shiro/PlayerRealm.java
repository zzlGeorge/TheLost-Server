package com.george.shiro;

import com.george.dao.entity.Player;
import com.george.dao.mappers.PlayerMapper;
import com.george.utils.CommonUtils;
import com.george.web.exception.ex.CustomException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author : George
 *         Description :
 *         Date : Created in 13:43 2018/4/2
 *         Modified By :
 */
public class PlayerRealm extends AuthorizingRealm {

    @Autowired
    private PlayerMapper playerMapper;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        Player player = new Player();
        player.setUserName(username);
        List<Player> players = playerMapper.findByEntity(player, null);
        if (players != null && players.size() > 0) {
            player = players.get(0);
            if (!player.getPassword().equals(CommonUtils.md5Password(password))) {
                throw new IncorrectCredentialsException("用户密码不正确！");
            }
        } else {
            throw new UnknownAccountException("用户名不存在或密码不正确！");
        }
        return new SimpleAuthenticationInfo(player, password, getName());
    }
}
