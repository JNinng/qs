package top.ninng.qs.gateway.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ninng.entity.UnifyResponse;

/**
 * @Author OhmLaw
 * @Date 2023/2/16 14:27
 * @Version 1.0
 */
public class GlobalException {

    // 全局异常拦截（拦截项目中的所有异常）
    @ResponseBody
    @ExceptionHandler
    public UnifyResponse<Object> handlerException(Exception e) {

        // 打印堆栈，以供调试
        //        System.out.println("全局异常---------------");
        e.printStackTrace();

        // 不同异常返回不同状态码
        UnifyResponse<Object> re = null;
        if (e instanceof NotLoginException) {
            // 如果是未登录异常
            NotLoginException ee = (NotLoginException) e;
            re = UnifyResponse.fail("您还未登录！", null);
        } else if (e instanceof NotRoleException) {
            // 如果是角色异常
            NotRoleException ee = (NotRoleException) e;
            re = UnifyResponse.fail("无此角色：！" + ee.getRole(), null);
        } else if (e instanceof NotPermissionException) {
            // 如果是权限异常
            NotPermissionException ee = (NotPermissionException) e;
            re = UnifyResponse.fail("无此角色：！" + ee.getCode(), null);
        } else {
            // 普通异常, 输出：500 + 异常信息
            re = UnifyResponse.fail(e.getMessage());
        }

        // 返回给前端
        return re;
    }
}
