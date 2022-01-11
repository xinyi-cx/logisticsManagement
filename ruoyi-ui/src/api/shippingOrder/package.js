import request from '@/utils/request'

// 查询面单列表
export function listPackage(query) {
  return request({
    url: '/system/package/list',
    method: 'get',
    params: query
  })
}

export function listPackageAll(query) {
  return request({
    url: '/system/package/listAll',
    method: 'get',
    params: query
  })
}

// 查询面单详细
export function getPackage(id) {
  return request({
    url: '/system/package/' + id,
    method: 'get'
  })
}

// 新增面单
export function addPackage(data) {
  return request({
    url: '/system/package',
    method: 'post',
    data: data
  })
}

// 新增面单 -所有
export function addPackageAll(data) {
  return request({
    url: '/system/package/addAll',
    method: 'post',
    data: data
  })
}

// 修改面单
export function updatePackage(data) {
  return request({
    url: '/system/package',
    method: 'put',
    data: data
  })
}

// 删除面单
export function delPackage(id) {
  return request({
    url: '/system/package/' + id,
    method: 'delete'
  })
}
