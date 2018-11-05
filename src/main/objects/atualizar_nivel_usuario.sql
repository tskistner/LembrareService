delimiter //

drop procedure if exists atualizar_nivel_usuario; //
create procedure atualizar_nivel_usuario(id_usuario_p integer, 
										 id_categoria_p integer, 
										 id_exercicio_p integer, 
										 ie_opcao_p text)
begin

declare qt_nivel_atual_w integer;
declare qt_reg_w integer;

select count(1), ifnull(max(qt_nivel),0)
into   qt_reg_w, qt_nivel_atual_w
from   categoria_nivel
where id_categoria = id_categoria_p
and   id_usuario = id_usuario_p;

if (ie_opcao_p = 'U') then
	set qt_nivel_atual_w = qt_nivel_atual_w + 1;
else
	set qt_nivel_atual_w = qt_nivel_atual_w - 1;
end if;

if (qt_reg_w = 0) then
	insert into categoria_nivel values (id_categoria_p,id_usuario_p,qt_nivel_atual_w);
else
	update categoria_nivel 
	set  qt_nivel = qt_nivel_atual_w
	where id_categoria = id_categoria_p
	and   id_usuario = id_usuario_p;
end if;

commit;

insert into historico_level (id_usuario, dt_atualizacao, qt_nivel, id_exercicio, id_categoria)
	values (id_usuario_p, CURRENT_TIMESTAMP, qt_nivel_atual_w, id_exercicio_p, id_categoria_p);
	
commit;

end; //