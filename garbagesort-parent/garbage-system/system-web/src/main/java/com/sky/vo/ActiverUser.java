package com.sky.vo;

import com.sky.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author sky
 * @create 2021-05-19 21:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiverUser implements Serializable {
    private User user;
    // 角色
    private List<String> roles = Collections.emptyList();
    // 权限
    private List<String> permissions = Collections.emptyList();
}
