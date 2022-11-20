/*
 * Copyright (c) 2021-2022, gaoweixuan (breeze-cloud@foxmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.breeze.boot.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.breeze.boot.core.utils.Result;
import com.breeze.boot.log.annotation.BreezeSysLog;
import com.breeze.boot.log.config.LogType;
import com.breeze.boot.system.domain.SysMsg;
import com.breeze.boot.system.domain.SysPlatform;
import com.breeze.boot.system.dto.MsgSearchDTO;
import com.breeze.boot.system.service.SysMsgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * 系统消息控制器
 *
 * @author gaoweixuan
 * @date 2022-11-20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys/msg")
@Tag(name = "系统消息管理模块", description = "SysPlatformController")
public class SysMsgController {

    /**
     * 系统消息服务
     */
    private final SysMsgService sysMsgService;

    /**
     * 列表
     *
     * @return {@link Result}<{@link Page}<{@link SysPlatform}>>
     */
    @Operation(summary = "列表")
    @PostMapping("/list")
    @PreAuthorize("hasAnyAuthority('sys:msg:list')")
    public Result<IPage<SysMsg>> list(@RequestBody MsgSearchDTO msgSearchDTO) {
        return Result.ok(this.sysMsgService.listPage(msgSearchDTO));
    }

    /**
     * 详情
     *
     * @param id id
     * @return {@link Result}
     */
    @Operation(summary = "详情")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAnyAuthority('sys:msg:info')")
    public Result<SysMsg> info(@PathVariable("id") Long id) {
        return Result.ok(this.sysMsgService.getById(id));
    }

    /**
     * 保存
     *
     * @param msg 消息
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "保存")
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('sys:msg:save')")
    @BreezeSysLog(description = "消息信息保存", type = LogType.SAVE)
    public Result<Boolean> save(@Validated @RequestBody SysMsg msg) {
        return Result.ok(this.sysMsgService.save(msg));
    }

    /**
     * 编辑
     * 修改
     *
     * @param msg 味精
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "修改")
    @PutMapping("/edit")
    @PreAuthorize("hasAnyAuthority('sys:msg:edit')")
    @BreezeSysLog(description = "消息信息修改", type = LogType.EDIT)
    public Result<Boolean> edit(@Validated @RequestBody SysMsg msg) {
        return Result.ok(this.sysMsgService.updateById(msg));
    }

    /**
     * 删除
     *
     * @param ids id
     * @return {@link Result}
     */
    @Operation(summary = "删除")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyAuthority('sys:msg:delete')")
    @BreezeSysLog(description = "消息信息删除", type = LogType.DELETE)
    public Result<Boolean> delete(@NotNull(message = "参数不能为空") @RequestBody Long[] ids) {
        return Result.ok(this.sysMsgService.removeByIds(Arrays.asList(ids)));
    }

}
