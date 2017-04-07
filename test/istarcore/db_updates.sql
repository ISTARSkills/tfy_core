ALTER TABLE "public"."istar_user"
ADD COLUMN "is_verified" bool;

ALTER TABLE "public"."istar_user"
ADD COLUMN "type" varchar(255);

ALTER TABLE "public"."istar_user" RENAME "type" TO "login_type";
