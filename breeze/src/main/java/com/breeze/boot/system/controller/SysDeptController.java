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

import cn.hutool.core.lang.tree.Tree;
import com.breeze.boot.core.Result;
import com.breeze.boot.system.domain.SysDept;
import com.breeze.boot.system.dto.DeptDTO;
import com.breeze.boot.system.service.SysDeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统部门控制器
 *
 * @author breeze
 * @date 2022-09-02
 */
@RestController
@RequestMapping("/sys/dept")
@Tag(name = "系统部门管理模块", description = "SysDeptController")
public class SysDeptController {

    /**
     * 系统部门服务
     */
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 列表
     *
     * @param deptDTO 部门dto
     * @return {@link Result}<{@link List}<{@link Tree}<{@link Long}>>>
     */
    @Operation(summary = "列表")
    @PostMapping("/list")
    @PreAuthorize("hasAnyAuthority('sys:dept:list')")
    public Result<List<Tree<Long>>> list(@RequestBody DeptDTO deptDTO) {
        return Result.ok(this.sysDeptService.listDept(deptDTO));
    }

    /**
     * 详情
     *
     * @param id id
     * @return {@link Result}
     */
    @Operation(summary = "详情", description = "目前详情接口未使用")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAnyAuthority('sys:dept:info')")
    public Result<SysDept> info(@PathVariable("id") Long id) {
        return Result.ok(sysDeptService.getById(id));
    }

    /**
     * 保存
     *
     * @param deptEntity 部门实体
     * @return {@link Result}
     */
    @Operation(summary = "保存")
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('sys:dept:save')")
    public Result<Boolean> save(@RequestBody SysDept deptEntity) {
        return Result.ok(sysDeptService.save(deptEntity));
    }

    /**
     * 更新
     *
     * @param deptEntity 部门实体
     * @return {@link Result}
     */
    @Operation(summary = "更新")
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('sys:dept:update')")
    public Result<Boolean> update(@RequestBody SysDept deptEntity) {
        return Result.ok(this.sysDeptService.updateById(deptEntity));
    }

    /**
     * 删除
     *
     * @param id id
     * @return {@link Result}
     */
    @Operation(summary = "删除")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyAuthority('sys:dept:delete')")
    public Result<Boolean> delete(@RequestParam Long id) {
        return this.sysDeptService.deleteById(id);
    }

}
