import request from '@/utils/request'

// 查询转寄关联关系列表
export function listRel(query) {
  return request({
    url: '/system/redirectrel/list',
    method: 'get',
    params: query
  })
}

// 查询转寄关联关系详细
export function getRel(id) {
  return request({
    url: '/system/redirectrel/' + id,
    method: 'get'
  })
}

export function getInfoByNewPackageId(newPackageId) {
  return request({
    url: '/system/redirectrel/getInfoByNewPackageId/' + newPackageId,
    method: 'get'
  })
}

// 新增转寄关联关系
export function addRel(data) {
  return request({
    url: '/system/redirectrel',
    method: 'post',
    data: data
  })
}

// 修改转寄关联关系
export function updateRel(data) {
  return request({
    url: '/system/redirectrel',
    method: 'put',
    data: data
  })
}

// 删除转寄关联关系
export function delRel(id) {
  return request({
    url: '/system/redirectrel/' + id,
    method: 'delete'
  })
}
