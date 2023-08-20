package com.linxi.gkos.conteoller;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.validation.annotation.Validated;

@Validated
public abstract class BaseController<T> {
    protected abstract IService<T> getService();
}
