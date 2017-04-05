DO
	$$
DECLARE lobjs CURSOR FOR select distinct batch_students.student_id, batch.course_id from batch_students, batch_group, batch where batch.batch_group_id = batch_group.id and batch_students.batch_group_id = batch_group.id;
l_row record;
	

BEGIN
	FOR lobj_row IN lobjs LOOP 
		FOR l_row in (select distinct module_course.course_id, lesson_cmsession.lesson_id from module_course, cmsession_module, lesson_cmsession where module_course.module_id = cmsession_module.module_id and cmsession_module.cmsession_id = lesson_cmsession.cmsession_id and module_course.course_id = lobj_row.course_id) LOOP
			EXECUTE 'INSERT INTO student_playlist (id, student_id, course_id, lesson_id, status) VALUES ((select COALESCE(max(id),0)+1 from student_playlist), '||lobj_row.student_id||', '||lobj_row.course_id||', '||l_row.lesson_id||', ''INCOMPLETE'');';		
		END LOOP;
END LOOP ;
END $$;
