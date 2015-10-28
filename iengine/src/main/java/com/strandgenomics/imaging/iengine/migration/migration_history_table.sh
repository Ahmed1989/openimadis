#!/bin/bash
echo "use image_storagedb;"
# i varies over the project number
for i in {1..107}
do
echo           "ALTER TABLE project_"$i"_record_history modify column modification_type ENUM ('RECORD_CREATED', 'RECORD_DELETED', 'ADDED_CHILD_RECORD', 'ADDED_PARENT_RECORD', 'ATTACHMENT_ADDED', 'ATTACHMENT_DELETED', 'USER_ANNOTATION_ADDED', 'USER_ANNOTATION_DELETED', 'USER_ANNOTATION_MODIFIED', 'USER_COMMENT_ADDED', 'USER_COMMENT_DELETED', 'VISUAL_OVERLAY_ADDED', 'VISUAL_OVERLAY_DELETED', 'VISUAL_ANNOTATION_ADDED', 'VISUAL_ANNOTATION_DELETED', 'TASK_EXECUTED', 'TASK_TERMINATED', 'TASK_FAILED', 'TASK_SUCCESSFUL', 'HAS_SHORTCUT', 'SHORTCUT_ADDED', 'RECORD_TRANSFERED', 'CUSTOM_HISTORY', 'PROFILE_APPLIED') NOT NULL;"
done