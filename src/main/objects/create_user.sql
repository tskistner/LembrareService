delimiter //

drop procedure if exists create_user; //
create procedure create_user (id_usuario_p integer)
begin

	declare id_categoria_w int(11);
	declare v_finished integer default 0;
	
	declare categorias cursor for select id_categoria from categoria;
	declare continue handler for not found set v_finished = 1;

	open categorias;
	insert_categorias: loop
	fetch categorias into id_categoria_w;
	if v_finished = 1 then
		leave insert_categorias;
	end if;
		insert into categoria_nivel values (id_categoria_w, id_usuario_p,0);
	end loop;
	close categorias;
	
	commit;
   
end; //