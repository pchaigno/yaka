; entete
extrn lirent:proc, ecrent:proc
extrn ecrbool:proc
extrn ecrch:proc, ligsuiv:proc
.model SMALL
.586

.CODE

debut :
STARTUPCODE

main :

; ouvbloc 6
enter 6, 0

; iload -2
push word ptr [bp-2]

; iload -4
push word ptr [bp-4]

; iconst 2
push word ptr 2

; idiv
pop bx
pop ax
cwd
idiv bx
push ax

; iadd
pop bx
pop ax
add ax, bx
push ax

; iconst 5
push word ptr 5

; idiv
pop bx
pop ax
cwd
idiv bx
push ax

; istore -6
pop ax
mov word ptr [bp-6], ax

; iload -4
push word ptr [bp-4]

; iconst 3
push word ptr 3

; iload -4
push word ptr [bp-4]

; imul
pop bx
pop ax
imul bx
push ax

; iadd
pop bx
pop ax
add ax, bx
push ax

; iconst 4
push word ptr 4

; isub
pop bx
pop ax
sub ax, bx
push ax

; istore -2
pop ax
mov word ptr [bp-2], ax

; queue
nop
EXITCODE
end debut