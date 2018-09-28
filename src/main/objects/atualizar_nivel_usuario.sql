delimiter //

drop procedure if exists atualizar_nivel_usuario; //
create procedure atualizar_nivel_usuario(id_usuario_p int, id_categoria_p int, id_exercicio_p int, ie_opcao_p text)
begin

declare qt_nivel_atual_w int;

select qt_nivel
into   qt_nivel_atual_w
from   categoria_nivel
where id_categoria = id_categoria_p
and   id_usuario = id_usuario_p;

if (ie_opcao_p = 'U') then
	set qt_nivel_atual_w = qt_nivel_atual_w + 1;
else
	set qt_nivel_atual_w = qt_nivel_atual_w - 1;
end if;

update categoria_nivel 
set  qt_nivel = qt_nivel_atual_w
where id_categoria = id_categoria_p
and   id_usuario = id_usuario_p;

commit;

insert into historico_level (id_usuario, dt_atualizacao, qt_nivel, id_exercicio, id_categoria)
	values (id_usuario_p, current_date, qt_nivel_atual_w, id_exercicio_p, id_categoria_p);
	
commit;


end; //