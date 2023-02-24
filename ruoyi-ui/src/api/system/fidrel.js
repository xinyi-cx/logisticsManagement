import request from '@/utils/request'

// 查询用户fid对应关系列表
export function listFidrel(query) {
  return request({
    url: '/system/fidrel/list',
    method: 'get',
    params: query
  })
}

// 查询用户fid对应关系详细
export function getFidrel(id) {
  return request({
    url: '/system/fidrel/' + id,
    method: 'get'
  })
}

// 新增用户fid对应关系
export function addFidrel(data) {
  return request({
    url: '/system/fidrel',
    method: 'post',
    data: data
  })
}

// 修改用户fid对应关系
export function updateFidrel(data) {
  return request({
    url: '/system/fidrel',
    method: 'put',
    data: data
  })
}

// 删除用户fid对应关系
export function delFidrel(id) {
  return request({
    url: '/system/fidrel/' + id,
    method: 'delete'
  })
}

export function activeFidrel(id) {
  return request({
    url: '/system/fidrel/active/'+ id,
    method: 'put'
  })
}
